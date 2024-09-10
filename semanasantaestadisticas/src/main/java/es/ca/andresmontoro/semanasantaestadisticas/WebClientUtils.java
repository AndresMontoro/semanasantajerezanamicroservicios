package es.ca.andresmontoro.semanasantaestadisticas;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public class WebClientUtils {

  public static <T> Mono<T> handleErrors(ClientResponse clientResponse, String nombreCiudad) {
    HttpStatusCode status = clientResponse.statusCode();

    String errorMessage;
    if (status.equals(HttpStatus.NOT_FOUND)) {
      errorMessage = "Recurso no encontrada con el nombre: " + nombreCiudad;
    } else if (status.equals(HttpStatus.BAD_REQUEST) || status.equals(HttpStatus.CONFLICT)) {
      errorMessage = "Error en la solicitud con código de estado: " + status;
    } else if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
      errorMessage = "Error en el servidor con código de estado: " + status;
    } else if (status.is4xxClientError()) {
      errorMessage = "Error en la solicitud con código de estado: " + status;
    } else if (status.is5xxServerError()) {
      errorMessage = "Error en el servidor con código de estado: " + status;
    } else {
      errorMessage = "Error inesperado con código de estado: " + status;
    }

    return Mono.error(new RuntimeException(errorMessage));
  }
}