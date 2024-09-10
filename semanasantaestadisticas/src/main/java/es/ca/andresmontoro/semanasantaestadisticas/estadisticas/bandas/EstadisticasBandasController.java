package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.bandas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/estadisticas/bandas")
@AllArgsConstructor
public class EstadisticasBandasController {

  private final EstadisticasBandasService estadisticasBandasService;

  @GetMapping("/masComunes/{lastNYears}")
  public Flux<MostCommonBandaResponse> getMostCommonBandaOnPeriod(
    @PathVariable int lastNYears
  ) {
    return estadisticasBandasService.getMostCommonBandaOnPeriod(lastNYears);
  }
}
