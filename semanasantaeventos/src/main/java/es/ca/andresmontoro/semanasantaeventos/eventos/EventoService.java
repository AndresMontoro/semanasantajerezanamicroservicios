package es.ca.andresmontoro.semanasantaeventos.eventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaeventos.hermandades.HermandadResponse;
import es.ca.andresmontoro.semanasantaeventos.hermandades.HermandadService;
import es.ca.andresmontoro.semanasantaeventos.tipoeventos.TipoEvento;
import es.ca.andresmontoro.semanasantaeventos.tipoeventos.TipoEventoService;
import es.ca.andresmontoro.semanasantaeventos.validators.Validator;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoService {
  private final EventoRepository eventoRepository;
  private final TipoEventoService tipoEventoService;
  private final HermandadService hermandadService;
  private KafkaTemplate<String, EventoResponse> kafkaTemplate;

  public Page<EventoResponse> findAll(Pageable pageable) {
    Page<Evento> eventos = eventoRepository.findAll(pageable);
    return eventos.map(EventoMapper::toResponse);
  }

  public Optional<Evento> findById(Long id) {
    return eventoRepository.findById(id);
  }

  public EventoResponse create(EventoDTO eventoDTO) {
    if (eventoDTO == null) {
      throw new IllegalArgumentException("EventoDTO no puede ser nulo");
    }

    TipoEvento tipoEvento = tipoEventoService.findById(eventoDTO.getIdTipoEvento())
      .orElseThrow(() -> new IllegalArgumentException("Tipo de evento no encontrado"));

    HermandadResponse hermandad = hermandadService
      .getHermandadById(eventoDTO.getIdHermandad()).block();

    if (hermandad == null) {
      throw new IllegalArgumentException("Hermandad no encontrada");
    }
    
    Evento eventoToCreate = EventoMapper.toEntity(eventoDTO, tipoEvento);
    EventoResponse createdEvento = EventoMapper.toResponse(eventoRepository.save(eventoToCreate));

    kafkaTemplate.send(
      "new-events-notifications", 
      createdEvento
    );

    return createdEvento;
  }

  public EventoResponse removeById(Long id) {
    if(!Validator.isIdValid(id)) {
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
    }
    
    Evento eventoToDelete = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
    
    remove(eventoToDelete);
    return EventoMapper.toResponse(eventoToDelete);
  }

  public void remove(Evento evento) {
    eventoRepository.delete(evento);
  }

  public EventoResponse update(Long id, EventoDTO eventoDTO) {
    if (eventoDTO == null) {
      throw new IllegalArgumentException("EventoDTO no puede ser nulo");
    }

    TipoEvento tipoEvento = tipoEventoService.findById(eventoDTO.getIdTipoEvento())
      .orElseThrow(() -> new IllegalArgumentException("Tipo de evento no encontrado"));

    Evento evento = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));

    evento.setNombre(eventoDTO.getNombre());
    evento.setDescription(eventoDTO.getDescription());
    evento.setFechaYHora(eventoDTO.getFechaYHora());
    evento.setTipoEvento(tipoEvento);

    return EventoMapper.toResponse(eventoRepository.save(evento));
  }
}
