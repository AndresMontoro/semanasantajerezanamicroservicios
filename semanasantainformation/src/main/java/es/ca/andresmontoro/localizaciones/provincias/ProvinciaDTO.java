package es.ca.andresmontoro.localizaciones.provincias;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ProvinciaDTO {
  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 64, message = "El nombre no puede tener más de 64 caracteres")
  private String nombre;

  private Long comunidadId;
}
