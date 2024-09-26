package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.ciudades;

import org.springframework.stereotype.Service;

import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoService;
import es.ca.andresmontoro.semanasantaestadisticas.estadisticas.FluxGenerator;
import es.ca.andresmontoro.semanasantaestadisticas.bandas.BandaService;
import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Year;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstadisticasCiudadesService {
  
  private final ContratoService contratoService;
  private final BandaService bandaService;

  public Flux<MostCommonCiudadResponse> getMostCommonCiudadesOnPeriod(int lastNYears) {
    return FluxGenerator.generateYearsFlux(lastNYears)
      .flatMap(year -> fetchAndBuildMostCommonCiudadOnYear(year));
  }

  private Mono<MostCommonCiudadResponse> fetchAndBuildMostCommonCiudadOnYear(Year year) {
    Flux<ContratoResponse> contratos = contratoService.getContratosOnYear(year);
    Flux<Long> ciudadesIds = getCiudadesIdsFromContratos(contratos);
    Mono<Long> mostCommonCiudadId = getMostCommonValueFromFlux(ciudadesIds);
    return buildMostCommonCiudadResponse(mostCommonCiudadId, contratos);
  }

  private Flux<Long> getCiudadesIdsFromContratos(Flux<ContratoResponse> contratos) {
    return contratos.flatMap(contrato -> {
      return bandaService.getBandaById(contrato.getBandaId())
        .flatMap(banda -> {
          return Mono.just(banda.getCiudadId());
        });
    });
  }

  private Mono<Long> getMostCommonValueFromFlux(Flux<Long> flux) {
    return flux
    .collect(Collectors.groupingBy(ciudadId -> ciudadId, Collectors.counting())) // Flux -> Mono<Map<Long, Long>>
    .flatMapMany(map -> Flux.fromIterable(map.entrySet())) // Convertimos el Map a un Flux de entradas
    .sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
    .next()
    .map(Map.Entry::getKey);
  }

  private Mono<MostCommonCiudadResponse> buildMostCommonCiudadResponse(Mono<Long> mostCommonCiudadId, Flux<ContratoResponse> contratos) {
    return mostCommonCiudadId.flatMap(ciudadId -> {
      return Mono.just(
        MostCommonCiudadResponse.builder()
          .ciudadId(ciudadId)
          .contratos(contratos.collectList().block())
          .build()
      );
    });
  }
}
