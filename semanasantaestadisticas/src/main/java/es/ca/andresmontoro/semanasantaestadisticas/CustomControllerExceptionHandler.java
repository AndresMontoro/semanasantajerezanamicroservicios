package es.ca.andresmontoro.semanasantaestadisticas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomControllerExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleException(IllegalArgumentException e) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(WebClientException.class)
  public ResponseEntity<?> handleException(CustomControllerExceptionHandler e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(WebClientRequestException.class)
  public ResponseEntity<?> handleException(WebClientRequestException e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<?> handleException(IllegalStateException e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    e.printStackTrace();
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
