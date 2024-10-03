package es.ca.andresmontoro.semanasantaeventos.Eventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaeventos.TipoEventos.TipoEvento;
import es.ca.andresmontoro.semanasantaeventos.TipoEventos.TipoEventoService;
import es.ca.andresmontoro.semanasantaeventos.validators.Validator;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoService {
  private final EventoRepository eventoRepository;
  private final TipoEventoService tipoEventoService;

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

    Evento evento = EventoMapper.toEntity(eventoDTO, tipoEvento);
    return EventoMapper.toResponse(eventoRepository.save(evento));
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
