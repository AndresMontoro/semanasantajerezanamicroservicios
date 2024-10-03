package es.ca.andresmontoro.semanasantaeventos.TipoEventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaeventos.validators.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TipoEventoService {
  private final TipoEventoRepository tipoEventoRepository;

  public Optional<TipoEvento> findById(Long id) {
    return tipoEventoRepository.findById(id);
  }

  public Page<TipoEventoResponse> findAll(Pageable pageable) {
    Page<TipoEvento> tipoEventos = tipoEventoRepository.findAll(pageable);
    return tipoEventos.map(TipoEventoMapper::toResponse);
  }

  public TipoEventoResponse create(TipoEventoDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("TipoEventoDTO no puede ser nulo");
    }

    TipoEvento tipoEvento = TipoEvento.builder()
      .nombre(dto.getNombre())
      .build();

    return TipoEventoMapper.toResponse(tipoEventoRepository.save(tipoEvento));
  }

  public TipoEventoResponse removeById(Long id) {
    if (!Validator.isIdValid(id)) {
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
    }

    TipoEvento tipoEvento = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("TipoEvento no encontrado"));

    remove(tipoEvento);
    return TipoEventoMapper.toResponse(tipoEvento);
  }

  public void remove(TipoEvento tipoEvento) {
    tipoEventoRepository.delete(tipoEvento);
  }

  public TipoEventoResponse update(Long id, TipoEventoDTO dto) {
    if(!Validator.isIdValid(id)) {
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
    }

    if (dto == null) {
      throw new IllegalArgumentException("TipoEventoDTO no puede ser nulo");
    }

    TipoEvento tipoEvento = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("TipoEvento no encontrado"));

    tipoEvento.setNombre(dto.getNombre());
    return TipoEventoMapper.toResponse(tipoEventoRepository.save(tipoEvento));
  }
}
