package es.ca.andresmontoro.semanasantaeventos.TipoEventos;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TipoEventoService {
  private final TipoEventoRepository tipoEventoRepository;

  public Optional<TipoEvento> findById(Long id) {
    return tipoEventoRepository.findById(id);
  }
}
