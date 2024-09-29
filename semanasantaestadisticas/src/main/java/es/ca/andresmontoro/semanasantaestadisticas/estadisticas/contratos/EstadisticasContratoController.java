package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.contratos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ssjerezana/estadisticas/contratos")
@AllArgsConstructor
public class EstadisticasContratoController {
  
  private final EstadisticasContratoService estadisticasContratoService;

  @GetMapping("/contratosCiudad/{lastNYears}")
  @CircuitBreaker(name = "estadisticasContratos", fallbackMethod = "fallBackMethod")
  public Flux<AnnioContrato> getContratosCiudadOnPeriod(
    @PathVariable int lastNYears,
    @RequestParam(required = true) String nombreCiudad
  ) {
    return estadisticasContratoService.getContratosCiudadOnPeriod(nombreCiudad, lastNYears);
  }

  // TODO: this must return a more sofisticated error and create a timeout
  public String fallBackMethod(
    int lastNYears,
    String nombreCiudad,
    RuntimeException runtimeException
  ) {
    return "Something went wrong";
  }
}
