package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.bandas;

import java.time.Year;

import es.ca.andresmontoro.semanasantaestadisticas.bandas.BandaResponse;
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
public class MostCommonBandaResponse {
  private Year year;
  private BandaResponse banda;
  private Integer numeroContratos;
}
