package es.ca.andresmontoro.semanasantaestadisticas.localidades;

import java.util.ArrayList;
import java.util.List;

import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoResponse;
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
public class CiudadNumContratosResponse {
  private int numContratos;
  private CiudadResponse ciudad;
  @Builder.Default
  private List<ContratoResponse> contratos = new ArrayList<>();
}