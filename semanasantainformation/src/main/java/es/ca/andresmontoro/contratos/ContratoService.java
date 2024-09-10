package es.ca.andresmontoro.contratos;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.bandas.Banda;
import es.ca.andresmontoro.bandas.BandaService;
import es.ca.andresmontoro.hermandades.Hermandad;
import es.ca.andresmontoro.hermandades.HermandadService;
import es.ca.andresmontoro.validators.Validator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ContratoService {
  private final ContratoRepository contratoRepository;
  private final HermandadService hermandadService;
  private final BandaService bandaService;

  public ContratoService(
    ContratoRepository contratoRepository,
    HermandadService hermandadService,
    BandaService bandaService
  ) {
    this.contratoRepository = contratoRepository;
    this.hermandadService = hermandadService;
    this.bandaService = bandaService;
  }

  public List<Contrato> findAll() {
    return this.contratoRepository.findAll();
  }

  public Page<ContratoResponse> findAll(Pageable pageable) {
    Page<Contrato> contratos = contratoRepository.findAll(pageable);
    return contratos.map(ContratoMapper::toResponse);
  }

  // Devolver un ContratoDTO
  public CompletableFuture<Contrato> create(ContratoDTO contrato) {
    CompletableFuture<Hermandad> hermandadFuture = hermandadService.findByIdAsync(contrato.getHermandadId())
      .thenApply(optionalHermandad -> optionalHermandad.orElseThrow(
        () -> new EntityNotFoundException("Hermandad no encontrada para el contrato")));

    CompletableFuture<Banda> bandaFuture = bandaService.findByIdAsync(contrato.getBandaId())
      .thenApply(optionalBanda -> optionalBanda.orElseThrow(
        () -> new EntityNotFoundException("Banda no encontrada para el contrato")));

    return hermandadFuture.thenCombine(bandaFuture, (hermandad, banda) -> {
      Contrato newContrato = Contrato.builder()
        .fechaInicio(contrato.getFechaInicio())
        .fechaFin(contrato.getFechaFin())
        .verdaderaFechaFin(contrato.getVerdaderaFechaFin())
        .hermandad(hermandad)
        .banda(banda)
        .build();

      return newContrato;
    }).thenCompose(newContrato -> CompletableFuture.supplyAsync(() -> contratoRepository.save(newContrato)));
  }

  public void remove(Contrato contrato) {
    this.contratoRepository.delete(contrato);
  }

  @Transactional
  public ContratoResponse removeById(Long id) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");

    Contrato contrato = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado"));

    this.contratoRepository.deleteById(id);
    return ContratoMapper.toResponse(contrato);
  }

  public ContratoResponse updateContrato(Long id, ContratoDTO updateContrato) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
      
    Contrato contrato = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado"));

    if (contrato.equals(updateContrato))
      return ContratoMapper.toResponse(contrato);

    // TO DO: Make these two calls async
    Hermandad hermandad = hermandadService.findById(updateContrato.getHermandadId())
      .orElseThrow(() -> new EntityNotFoundException("Hermandad no encontrada"));

    Banda banda = bandaService.findById(updateContrato.getBandaId())
      .orElseThrow(() -> new EntityNotFoundException("Banda no encontrada"));

    contrato.setFechaInicio(updateContrato.getFechaInicio());
    contrato.setFechaFin(updateContrato.getFechaFin());
    contrato.setVerdaderaFechaFin(updateContrato.getVerdaderaFechaFin());
    contrato.setHermandad(hermandad);
    contrato.setBanda(banda);

    Contrato update = contratoRepository.save(contrato);
    return ContratoMapper.toResponse(update);
  }

  public Optional<Contrato> findById(Long id) {
    return contratoRepository.findById(id);
  }

  @Async
  public CompletableFuture<Optional<Contrato>> findByIdAsync(Long id) {
    return CompletableFuture.completedFuture(contratoRepository.findById(id));
  }

  public List<ContratoResponse> getContratosAnnioCiudad(Year year, Long idCiudad) {
    LocalDate beginOfYear = LocalDate.of(year.getValue(), 1, 1);
    LocalDate endOfYear = LocalDate.of(year.getValue(), 12, 31);

    return contratoRepository.getContratosAnnioCiudad(beginOfYear, endOfYear, idCiudad);
  }

  public List<ContratoResponse> getContratosAnnio(Year annio) {
    LocalDate beginOfYear = LocalDate.of(annio.getValue(), 1, 1);
    LocalDate endOfYear = LocalDate.of(annio.getValue(), 12, 31);

    return contratoRepository.findByAnnio(beginOfYear, endOfYear);
  }
}
