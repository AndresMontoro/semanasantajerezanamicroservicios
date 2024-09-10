package es.ca.andresmontoro.localizaciones.provincias;

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
public class ProvinciaResponse {
  private Long id;
  private String nombre;
  private Long comunidadId;
}
