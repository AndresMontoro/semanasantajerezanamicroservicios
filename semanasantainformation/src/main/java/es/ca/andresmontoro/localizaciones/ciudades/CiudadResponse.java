package es.ca.andresmontoro.localizaciones.ciudades;

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
public class CiudadResponse {
  private Long id;
  private String nombre;
  private Long provinciaId;
  private Long comunidadId;
}
