package es.ca.andresmontoro.semanasantaeventos.tipoeventos;

import es.ca.andresmontoro.semanasantaeventos.validators.NoSpecialCharacters;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TipoEventoDTO {
  @NotNull(message = "El nombre no puede ser nulo")
  @Size(min = 1, max = 128, message = "El nombre debe tener entre 1 y 128 caracteres")
  @NoSpecialCharacters(message = "El nombre no puede contener caracteres especiales")
  private String nombre;
}
