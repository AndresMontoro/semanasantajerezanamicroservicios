package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.contratos;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AnnioContrato {
  private Year annio;

  private Integer numeroContratos;
  
  @Builder.Default
  private List<ContratoResponse> contratos = new ArrayList<>();
}
