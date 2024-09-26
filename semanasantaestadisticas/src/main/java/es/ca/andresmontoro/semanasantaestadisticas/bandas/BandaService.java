package es.ca.andresmontoro.semanasantaestadisticas.bandas;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BandaService {
  private final WebClient.Builder webClientBuilder;

  public List<BandaResponse> getBandasByCiudad(Long ciudadId) {
    List<BandaResponse> bandas = webClientBuilder.build().get()
      .uri("http://semanasanta-information/ssjerezana/informacion/banda/ciudad/{ciudadId}", ciudadId)
      .retrieve()
      .bodyToFlux(BandaResponse.class)
      .collectList()
      .block();

    return bandas;
  }

  public Mono<BandaResponse> getBandaById(Long commonBandaId) {
    return webClientBuilder.build().get()
      .uri("http://semanasanta-information/ssjerezana/informacion/banda/{bandaId}", commonBandaId)
      .retrieve()
      .bodyToMono(BandaResponse.class);
  }
}
