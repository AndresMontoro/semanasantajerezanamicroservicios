package es.ca.andresmontoro.semanasantaestadisticas.localidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CiudadResponse {
  private Long id;
  private String nombre;
  private Long provinciaId;
  private Long comunidadId;
}
