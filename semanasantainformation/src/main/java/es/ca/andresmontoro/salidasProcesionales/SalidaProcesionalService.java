package es.ca.andresmontoro.salidasProcesionales;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.contratos.Contrato;
import es.ca.andresmontoro.contratos.ContratoService;
import es.ca.andresmontoro.hermandades.Hermandad;
import es.ca.andresmontoro.hermandades.HermandadService;
import es.ca.andresmontoro.tipoSalidasProcesionales.TipoSalidaProcesional;
import es.ca.andresmontoro.tipoSalidasProcesionales.TipoSalidaProcesionalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SalidaProcesionalService {
  private final SalidaProcesionalRepository salidaProcesionalRepository;
  private final HermandadService hermandadService;
  private final ContratoService contratoService;
  private final TipoSalidaProcesionalService tipoSalidaProcesionalService;

  public SalidaProcesionalService(
      SalidaProcesionalRepository salidaProcesionalRepository,
      HermandadService hermandadService,
      TipoSalidaProcesionalService tipoSalidaProcesionalService,
      ContratoService contratoService) {
    this.salidaProcesionalRepository = salidaProcesionalRepository;
    this.hermandadService = hermandadService;
    this.tipoSalidaProcesionalService = tipoSalidaProcesionalService;
    this.contratoService = contratoService;
  }

  public List<SalidaProcesional> findAll() {
    return salidaProcesionalRepository.findAll();
  }

  public Page<SalidaProcesional> findAll(Pageable pageable) {
    return salidaProcesionalRepository.findAll(pageable);
  }

  public SalidaProcesional create(SalidaProcesionalDTO salidaProcesional) {
    Hermandad hermandad = hermandadService.findById(salidaProcesional.getHermandadId())
      .orElseThrow(() -> new EntityNotFoundException("Hermandad no encontrada"));

    TipoSalidaProcesional tipo = tipoSalidaProcesionalService.findById(salidaProcesional.getTipoId())
      .orElseThrow(() -> new EntityNotFoundException("Tipo de salida procesional no encontrado"));

    Contrato contrato = contratoService.findById(salidaProcesional.getContratoId())
      .orElse(null);

    SalidaProcesional salida = SalidaProcesional.Builder.builder()
      .withFecha(salidaProcesional.getFecha())
      .withClima(salidaProcesional.getClima())
      .withHermandad(hermandad)
      .withContrato(contrato)
      .withTipo(tipo)
      .build();

    return salidaProcesionalRepository.save(salida);
  }

  @Transactional
  public void remove(SalidaProcesional salidaProcesional) {
    salidaProcesionalRepository.delete(salidaProcesional);
  }

  @Transactional
  public void removeById(Long id) {
    if (findById(id).isEmpty())
      throw new EntityNotFoundException("Salida procesional no encontrada");

    salidaProcesionalRepository.deleteById(id);
  }

  @Transactional
  public SalidaProcesional updateSalidaProcesional(Long id, SalidaProcesionalDTO updateSalidaProcesional) {
    SalidaProcesional salidaProcesional = findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Salida procesional no encontrada"));

    Hermandad hermandad = hermandadService.findById(updateSalidaProcesional.getHermandadId())
      .orElseThrow(() -> new EntityNotFoundException("Hermandad no encontrada"));

    Contrato contrato = contratoService.findById(updateSalidaProcesional.getContratoId())
      .orElse(null);

    TipoSalidaProcesional tipo = tipoSalidaProcesionalService.findById(updateSalidaProcesional.getTipoId())
      .orElseThrow(() -> new EntityNotFoundException("Tipo de salida procesional no encontrado"));

    salidaProcesional.setFecha(updateSalidaProcesional.getFecha());
    salidaProcesional.setClima(updateSalidaProcesional.getClima());
    salidaProcesional.setHermandad(hermandad);
    salidaProcesional.setContrato(contrato);
    salidaProcesional.setTipo(tipo);

    return salidaProcesionalRepository.save(salidaProcesional);
  }

  private Optional<SalidaProcesional> findById(Long id) {
    return salidaProcesionalRepository.findById(id);
  }
}
