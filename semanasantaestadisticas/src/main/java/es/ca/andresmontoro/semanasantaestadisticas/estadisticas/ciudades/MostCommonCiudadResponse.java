package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.ciudades;

import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MostCommonCiudadResponse {

  private Long ciudadId;

  @Builder.Default
  private List<ContratoResponse> contratos = new ArrayList<>();
}
