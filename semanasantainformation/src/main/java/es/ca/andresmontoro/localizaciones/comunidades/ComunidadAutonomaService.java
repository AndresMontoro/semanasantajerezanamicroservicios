package es.ca.andresmontoro.localizaciones.comunidades;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.CrudService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ComunidadAutonomaService implements CrudService<ComunidadAutonoma, ComunidadAutonoma> {
  private final ComunidadAutonomaRepository comunidadAutonomaRepository;

  public ComunidadAutonomaService(ComunidadAutonomaRepository comunidadAutonomaRepository) {
    this.comunidadAutonomaRepository = comunidadAutonomaRepository;
  }

  @Override
  public Page<ComunidadAutonoma> findAll(Pageable pageable) {
    return comunidadAutonomaRepository.findAll(pageable);
  }

  @Override
  public ComunidadAutonoma create(ComunidadAutonoma comunidad) {
    return comunidadAutonomaRepository.save(comunidad);
  }

  @Override
  public void remove(ComunidadAutonoma entity) {
    comunidadAutonomaRepository.delete(entity);
  }

  @Override
  public void removeById(Long id) {
    ComunidadAutonoma comunidad = comunidadAutonomaRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Comunidad autónoma no encontrada"));

    remove(comunidad);
  }

  @Override
  public ComunidadAutonoma update(Long id, ComunidadAutonoma dto) {
    ComunidadAutonoma comunidad = comunidadAutonomaRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Comunidad autónoma no encontrada"));

    comunidad.setNombre(dto.getNombre());
    return comunidadAutonomaRepository.save(comunidad);
  }

  @Override
  public Optional<ComunidadAutonoma> findById(Long id) {
    return comunidadAutonomaRepository.findById(id);
  }  
}
