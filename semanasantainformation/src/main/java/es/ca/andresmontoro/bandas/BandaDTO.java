package es.ca.andresmontoro.bandas;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
public class BandaDTO {
  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 128, message = "El nombre no puede tener más de 128 caracteres")
  @NoSpecialCharacters
  private String nombre;

  @NotNull(message = "La banda debe tener algún estilo")
  private Estilo_Banda estilo;

  @NotNull(message = "La banda debe tener un número aproximado de componentes")
  @Range(min = 0, message = "El número de componentes aproximado debe ser mayor o igual a 0")
  private Integer numeroComponentesAprox;

  @NotNull(message = "La banda debe tener una fecha de fundación")
  @PastOrPresent(message = "La fecha de fundación debe ser en el pasado o presente")
  private LocalDate fundacion;

  @NotNull(message = "La ciudad no puede ser nula")
  @Range(min = 1, message = "El id de la ciudad debe ser mayor o igual a 1")
  private Long ciudadId;
}
