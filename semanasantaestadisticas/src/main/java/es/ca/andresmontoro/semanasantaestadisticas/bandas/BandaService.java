package es.ca.andresmontoro.semanasantaestadisticas.bandas;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BandaService {
  private final WebClient webClient;

  public List<BandaResponse> getBandasByCiudad(Long ciudadId) {
    List<BandaResponse> bandas = webClient.get()
      .uri("http://localhost:8080/api/v1/banda/ciudad/{ciudadId}", ciudadId)
      .retrieve()
      .bodyToFlux(BandaResponse.class)
      .collectList()
      .block();

    return bandas;
  }

  public Mono<BandaResponse> getBandaById(Long commonBandaId) {
    return webClient.get()
      .uri("http://localhost:8080/api/v1/banda/{bandaId}", commonBandaId)
      .retrieve()
      .bodyToMono(BandaResponse.class);
  }
}
