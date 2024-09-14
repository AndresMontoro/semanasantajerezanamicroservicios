package es.ca.andresmontoro.semanasantaestadisticas.contratos;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import es.ca.andresmontoro.semanasantaestadisticas.localidades.CiudadResponse;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ContratoService {

  @Autowired
  private WebClient.Builder webClientBuilder;

  public Flux<ContratoResponse> getContratosOnYearCiudad(Year year, CiudadResponse ciudad) {
    return webClientBuilder.build().get()
      .uri(
        "http://semanasanta-information/api/v1/contrato/annioCiudad",
        uriBuilder -> uriBuilder
          .queryParam("annio", year.getValue())
          .queryParam("idCiudad", ciudad.getId())
          .build()
      )
      .retrieve()
      .onStatus(
        HttpStatusCode::isError, 
        response -> response.bodyToMono(String.class).map(Exception::new)
      )
      .bodyToFlux(ContratoResponse.class);
  }

  public Flux<ContratoResponse> getContratosOnYear(Year year) {
    return webClientBuilder.build().get()
      .uri(
        "http://semanasanta-information/api/v1/contrato/annio/{year}", year.getValue()
      )
      .retrieve()
      .onStatus(
        HttpStatusCode::isError, 
        response -> response.bodyToMono(String.class).map(Exception::new)
      )
      .bodyToFlux(ContratoResponse.class);
  }

  public Flux<ContratoResponse> getContratosMostCommonBandaOnYear(Year year) {
    return getContratosOnYear(year)
      .collectMultimap(ContratoResponse::getBandaId)
      .flatMapMany(contratoToBandaId -> {
        Long mostCommonBandaId = contratoToBandaId
          .keySet()
          .stream()
          .max((b1, b2) -> contratoToBandaId.get(b1).size() - contratoToBandaId.get(b2).size()) // Positive number is b1 > b2
          .orElse(null);

        return Flux.fromIterable(contratoToBandaId.get(mostCommonBandaId));
      });
  }
}
