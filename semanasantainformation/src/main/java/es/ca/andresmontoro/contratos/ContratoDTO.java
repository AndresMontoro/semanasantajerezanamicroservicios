package es.ca.andresmontoro.contratos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratoDTO {
  @NotNull(message = "La fecha de inicio no puede ser nula")
  @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
  private LocalDate fechaInicio;

  @NotNull(message = "La fecha de fin no puede ser nula")
  @Future(message = "La fecha de fin debe ser en el futuro")
  private LocalDate fechaFin;

  @PastOrPresent(message = "La verdadera fecha de fin debe ser en el pasado o presente")
  private LocalDate verdaderaFechaFin;

  @NotNull(message = "El id de la banda no puede ser nulo")
  @Range(min = 1, message = "El id de la banda debe ser mayor que 0")
  private Long bandaId;

  @NotNull(message = "El id de la hermandad no puede ser nulo")
  @Range(min = 1, message = "El id de la hermandad debe ser mayor que 0")
  private Long hermandadId;
}
