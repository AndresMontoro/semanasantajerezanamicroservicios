package es.ca.andresmontoro.localizaciones.ciudades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.localizaciones.provincias.ProvinciaService;
import jakarta.persistence.EntityNotFoundException;
import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonoma;
import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonomaService;
import es.ca.andresmontoro.localizaciones.provincias.Provincia;

@Service
public class CiudadService {
  private final CiudadRepository ciudadRepository;
  private final ProvinciaService provinciaService;
  private final ComunidadAutonomaService comunidadAutonomaService;

  public CiudadService(
    CiudadRepository ciudadRepository,
    ProvinciaService provinciaService,
    ComunidadAutonomaService comunidadAutonomaService
  ) {
    this.ciudadRepository = ciudadRepository;
    this.provinciaService = provinciaService;
    this.comunidadAutonomaService = comunidadAutonomaService;
  }

  public Page<CiudadResponse> findAll(Pageable pageable) {
    Page<Ciudad> ciudades = ciudadRepository.findAll(pageable);
    return ciudades.map(CiudadMapper::toResponse);
  }

  public Ciudad create(CiudadDTO ciudad) {
    final Provincia provincia = (ciudad.getProvinciaId() != null) 
      ? provinciaService.findById(ciudad.getProvinciaId()).orElseThrow()
      : null;
    
    final ComunidadAutonoma comunidad = (ciudad.getProvinciaId() == null) 
      ? comunidadAutonomaService.findById(ciudad.getComunidadId()).orElseThrow()
      : null;

    Ciudad newCiudad = Ciudad.builder()
      .nombre(ciudad.getNombre())
      .provincia(provincia)
      .comunidad(comunidad)
      .build();

    return ciudadRepository.save(newCiudad);
  }

  public void remove(Ciudad ciudad) {
    ciudadRepository.delete(ciudad);
  }

  public void removeById(Long id) {
    Ciudad ciudad = ciudadRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

    ciudadRepository.delete(ciudad);
  }

  public Ciudad update(Long id, CiudadDTO updateCiudad) {
    Ciudad ciudad = ciudadRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

    if (ciudad.equals(updateCiudad))
      return ciudad;

    final Provincia provincia = (updateCiudad.getProvinciaId() != null) 
      ? provinciaService.findById(updateCiudad.getProvinciaId())
        .orElseThrow(() -> new EntityNotFoundException("Provincia no válida"))
      : null;

    final ComunidadAutonoma comunidad = (updateCiudad.getComunidadId() != null)
      ? comunidadAutonomaService.findById(updateCiudad.getComunidadId())
        .orElseThrow(() -> new EntityNotFoundException("Comunidad autónoma no válida"))
      : null;
    
    ciudad.setNombre(updateCiudad.getNombre());
    ciudad.setProvincia(provincia);
    ciudad.setComunidad(comunidad);
    return ciudadRepository.save(ciudad);
  }

  public List<CiudadResponse> findByProvincia(Integer provinciaId) {
    List<Ciudad> ciudades = ciudadRepository.findByProvincia(provinciaId);
    return ciudades.stream()
      .map(CiudadMapper::toResponse)
      .collect(Collectors.toList());
  }

  public Optional<Ciudad> findById(Long ciudadId) {
    return ciudadRepository.findById(ciudadId);
  }

  public CiudadResponse findByName(String string) {
    System.out.println("Searching city by name: " + string);
    Ciudad ciudad = ciudadRepository.findByNombreTrimmed(string)
      .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));
    System.out.println("City found: " + ciudad.getNombre());
    
    CiudadResponse ciudadResponse = CiudadResponse.builder()
      .id(ciudad.getId())  
      .nombre(ciudad.getNombre())
      .provinciaId(
        (ciudad.getProvincia() != null) 
        ? ciudad.getProvincia().getId() 
        : null
      )
      .comunidadId(
        (ciudad.getComunidad() != null) 
        ? ciudad.getComunidad().getId() 
        : null
      )
      .build();

    return ciudadResponse;
  }
}
