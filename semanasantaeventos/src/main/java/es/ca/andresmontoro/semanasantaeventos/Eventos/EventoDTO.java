package es.ca.andresmontoro.semanasantaeventos.Eventos;

import org.hibernate.validator.constraints.Range;

import es.ca.andresmontoro.semanasantaeventos.validators.NoSpecialCharacters;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventoDTO {
  @NotNull(message = "El nombre del evento no puede ser nulo")
  @Size(min = 1, max = 128, message = "El nombre del evento debe tener entre 1 y 100 caracteres")
  @NoSpecialCharacters(message = "El nombre del evento no puede contener caracteres especiales")
  private String nombre;

  @NotNull(message = "La descripción del evento no puede ser nula")
  @Size(min = 1, max = 1024, message = "La descripción del evento debe tener entre 1 y 1024 caracteres")
  @NoSpecialCharacters(message = "El nombre del evento no puede contener caracteres especiales")
  private String description;

  @NotNull(message = "La fecha y hora del evento no puede ser nula")
  private LocalDateTime fechaYHora;

  @NotNull(message = "La duración del evento no puede ser nula")
  @Range(min = 1, message = "El id de la hermandad no puede ser menor que 1")
  private Long idHermandad;

  @NotNull(message = "El tipo de evento no puede ser nulo")
  @Range(min = 1, message = "El id del tipo de evento no puede ser menor que 1")
  private Long idTipoEvento;
}
