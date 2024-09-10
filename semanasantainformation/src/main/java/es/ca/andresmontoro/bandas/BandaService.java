package es.ca.andresmontoro.bandas;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.localizaciones.ciudades.Ciudad;
import es.ca.andresmontoro.localizaciones.ciudades.CiudadService;
import es.ca.andresmontoro.validators.Validator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BandaService {
  private final BandaRepository bandaRepository;
  private final CiudadService ciudadService;

  public BandaService(BandaRepository bandaRepository, CiudadService ciudadService) {
    this.bandaRepository = bandaRepository;
    this.ciudadService = ciudadService;
  }

  public List<Banda> findAll() {
    return this.bandaRepository.findAll();
  }

  public Page<BandaResponse> findAll(Pageable pageable) {
    Page<Banda> bandas = bandaRepository.findAll(pageable);
    return bandas.map(BandaMapper::toResponse);
  }

  public BandaResponse create(BandaDTO banda) {
    Ciudad ciudad = this.ciudadService.findById(banda.getCiudadId())
        .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Ciudad no encontrada"));

    Banda newBanda = Banda.builder()
        .nombre(banda.getNombre())
        .estilo(banda.getEstilo())
        .numeroComponentesAprox(banda.getNumeroComponentesAprox())
        .fundacion(banda.getFundacion())
        .ciudad(ciudad)
        .build();

    Banda bandaResponse = this.bandaRepository.save(newBanda);
    return BandaMapper.toResponse(bandaResponse);
  }

  public void removeBanda(Banda banda) {
    this.bandaRepository.delete(banda);
  }

  @Transactional
  public BandaResponse removeById(Long id) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");

    Banda banda = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Banda no encontrada"));

    removeBanda(banda);
    return BandaMapper.toResponse(banda);
  }

  public BandaResponse updateBanda(Long id, BandaDTO updateBanda) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");

    Banda banda = findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Banda no encontrada"));

    if (banda.equals(updateBanda))
      return BandaMapper.toResponse(banda);

    Ciudad ciudad = ciudadService.findById(updateBanda.getCiudadId())
        .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

    banda.setNombre(updateBanda.getNombre());
    banda.setEstilo(updateBanda.getEstilo());
    banda.setNumeroComponentesAprox(updateBanda.getNumeroComponentesAprox());
    banda.setFundacion(updateBanda.getFundacion());
    banda.setCiudad(ciudad);

    Banda bandaResponse = bandaRepository.save(banda);
    return BandaMapper.toResponse(bandaResponse);
  }

  public Optional<Banda> findById(Long id) {
    return this.bandaRepository.findById(id);
  }

  public BandaResponse findByIdAndReturnBandaResponse(Long id) {
    Banda banda = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Banda no encontrada"));

    return BandaMapper.toResponse(banda);
  }

  @Async("asyncTaskExecutor")
  public CompletableFuture<Optional<Banda>> findByIdAsync(Long id) {
    return CompletableFuture.completedFuture(bandaRepository.findById(id));
  }

  public List<BandaResponse> findByCiudad(Long idCiudad) {
    if (!Validator.isIdValid(idCiudad))
      throw new IllegalArgumentException("El id de la ciudad no puede ser nulo o menor que 1");

    Ciudad ciudad = ciudadService.findById(idCiudad)
      .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

    List<Banda> bandas = this.bandaRepository.findByCiudad(ciudad);
    return bandas.stream()
      .map(BandaMapper::toResponse)
      .toList();
  }
}
