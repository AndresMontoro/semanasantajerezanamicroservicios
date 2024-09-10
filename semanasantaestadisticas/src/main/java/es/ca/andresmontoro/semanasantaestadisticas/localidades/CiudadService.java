package es.ca.andresmontoro.semanasantaestadisticas.localidades;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CiudadService {

  private final WebClient webClient;

  public Mono<CiudadResponse> getCiudadByName(String nombreCiudad) {
    return webClient.get()
      .uri(
        "http://localhost:8080/api/v1/ciudad/findByName",
        uriBuilder -> uriBuilder.queryParam("nombre", nombreCiudad).build()
      )
      .retrieve()
      .onStatus(
        HttpStatusCode::isError,
        clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new)
      )
      .bodyToMono(CiudadResponse.class)
      .switchIfEmpty(Mono.error(new IllegalArgumentException(String.format("Ciudad no encontrada: %s", nombreCiudad))));
  }
}
