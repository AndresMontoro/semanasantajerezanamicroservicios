package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.bandas;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaestadisticas.bandas.BandaService;
import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoService;
import es.ca.andresmontoro.semanasantaestadisticas.estadisticas.FluxGenerator;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EstadisticasBandasService {
  private final BandaService bandaService;
  private final ContratoService contratoService;

  public Flux<MostCommonBandaResponse> getMostCommonBandaOnPeriod(int lastNYears) {
    return FluxGenerator.generateYearsFlux(lastNYears)
      .flatMap(year -> fetchAndBuildMostCommonBandaResponse(year));
  }

  private Mono<MostCommonBandaResponse> fetchAndBuildMostCommonBandaResponse(Year year) {
    return contratoService.getContratosMostCommonBandaOnYear(year)
      .collectList()  //Devuelve un Mono<List<ContratoResponse>>
      .flatMap(contratos -> buildMostCommonBandaResponse(year, contratos));
  }

  private Mono<MostCommonBandaResponse> buildMostCommonBandaResponse(Year year,
    List<ContratoResponse> contratos) {
    if(contratos.isEmpty()) 
      return Mono.empty();

    ContratoResponse contrato = contratos.get(0);
    if(contrato.getBandaId() == null)
      return Mono.empty();

    return bandaService.getBandaById(contrato.getBandaId())
      .flatMap(banda -> Mono.just( 
        MostCommonBandaResponse.builder()
          .year(year)
          .banda(banda)
          .numeroContratos(contratos.size())
          .build()
      ));
  }
}
