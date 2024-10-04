package es.ca.andresmontoro.semanasantaeventos.hermandades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;


import reactor.core.publisher.Mono;

@Service
public class HermandadService {
  @Autowired
  private WebClient.Builder webClientBuilder;

  public Mono<HermandadResponse> getHermandadById(Long id) {
    return webClientBuilder.build().get()
      .uri(
        String.format("http://semanasanta-information/ssjerezana/informacion/hermandad/%d", id)
      )
      .retrieve()
      .onStatus(
        HttpStatusCode::isError, 
        response -> response.bodyToMono(String.class).map(Exception::new)
      )
      .bodyToMono(HermandadResponse.class);
  }
}
