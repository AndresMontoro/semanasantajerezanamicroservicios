package es.ca.andresmontoro.semanasantaestadisticas.estadisticas;

import java.time.Year;

import reactor.core.publisher.Flux;

public class FluxGenerator {
  
  public static Flux<Year> generateYearsFlux(int lastNYears) {
    return Flux.range(0, lastNYears)
      .map(i -> Year.now().minusYears(i));
  }

  private FluxGenerator() {
    throw new IllegalStateException("Utility class");
  }
}
