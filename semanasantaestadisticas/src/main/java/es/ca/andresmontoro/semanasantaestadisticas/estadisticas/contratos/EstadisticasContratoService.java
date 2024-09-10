package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.contratos;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoService;
import es.ca.andresmontoro.semanasantaestadisticas.estadisticas.FluxGenerator;
import es.ca.andresmontoro.semanasantaestadisticas.localidades.CiudadResponse;
import es.ca.andresmontoro.semanasantaestadisticas.localidades.CiudadService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EstadisticasContratoService {

  private final ContratoService contratoService;
  private final CiudadService ciudadService;

  public Flux<AnnioContrato> getContratosCiudadOnPeriod(String nombreCiudad, int lastNYears) {
    return ciudadService.getCiudadByName(nombreCiudad)
      .flatMapMany(ciudad -> FluxGenerator.generateYearsFlux(lastNYears)
        .flatMap(year -> fetchAndBuildAnnioContrato(year, ciudad)));
  }

  private Mono<AnnioContrato> fetchAndBuildAnnioContrato(Year year, CiudadResponse ciudad) {
    return contratoService.getContratosOnYearCiudad(year, ciudad)
      .collectList()
      .map(contratos -> buildAnnioContrato(year, contratos));
  }

  private AnnioContrato buildAnnioContrato(Year year, List<ContratoResponse> contratos) {
    return AnnioContrato.builder()
      .annio(year)
      .numeroContratos(contratos.size())
      .contratos(contratos)
      .build();
  }
}
