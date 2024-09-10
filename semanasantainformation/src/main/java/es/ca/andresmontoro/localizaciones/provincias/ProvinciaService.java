package es.ca.andresmontoro.localizaciones.provincias;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonoma;
import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonomaService;
import es.ca.andresmontoro.validators.Validator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProvinciaService {
  private final ProvinciaRepository provinciaRepository;
  private final ComunidadAutonomaService comunidadAutonomaService;

  public ProvinciaService(
    ProvinciaRepository provinciaRepository,
    ComunidadAutonomaService comunidadAutonomaService
  ) {
    this.provinciaRepository = provinciaRepository;
    this.comunidadAutonomaService = comunidadAutonomaService;
  }

  public List<ProvinciaResponse> findAll() {
    return provinciaRepository.findAll().stream()
      .map(ProvinciaMapper::toResponse)
      .collect(Collectors.toList());
  }

  public List<ProvinciaResponse> findByComunidadAutonoma(Integer comunidadAutonomaId) {
    List<Provincia> provincias = this.provinciaRepository.findByComunidadAutonoma(comunidadAutonomaId);
    return provincias.stream()
      .map(ProvinciaMapper::toResponse)
      .collect(Collectors.toList());
  }

  public Page<ProvinciaResponse> findAll(Pageable pageable) {
    return provinciaRepository.findAll(pageable)
      .map(ProvinciaMapper::toResponse);
  }

  public ProvinciaResponse create(ProvinciaDTO dto) {
    ComunidadAutonoma comunidad = comunidadAutonomaService.findById(dto.getComunidadId())
      .orElseThrow(() -> new EntityNotFoundException("Comunidad autónoma no encontrada"));

    Provincia provincia = Provincia.builder()
      .nombre(dto.getNombre())
      .comunidadAutonoma(comunidad)
      .build();

    Provincia provinciaResponse = provinciaRepository.save(provincia);
    return ProvinciaMapper.toResponse(provinciaResponse);
  }

  public void remove(Provincia entity) {
    provinciaRepository.delete(entity);
  }

  public ProvinciaResponse removeById(Long id) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");

    Provincia provincia = provinciaRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada"));

    remove(provincia);
    return ProvinciaMapper.toResponse(provincia);
  }

  public ProvinciaResponse update(Long id, ProvinciaDTO dto) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
      
    Provincia provincia = provinciaRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada"));

    if(provincia.equals(dto))
      return ProvinciaMapper.toResponse(provincia);

    ComunidadAutonoma comunidad = comunidadAutonomaService.findById(dto.getComunidadId())
      .orElseThrow(() -> new EntityNotFoundException("Comunidad autónoma no encontrada"));

    provincia.setNombre(dto.getNombre());
    provincia.setComunidadAutonoma(comunidad);
    Provincia provinciaResponse = provinciaRepository.save(provincia);
    return ProvinciaMapper.toResponse(provinciaResponse);
  }

  public Optional<Provincia> findById(Long id) {
    return this.provinciaRepository.findById(id);
  }
}
