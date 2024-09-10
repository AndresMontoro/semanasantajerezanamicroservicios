package es.ca.andresmontoro.semanasantaestadisticas.contratos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContratoResponse {
  private Long id;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private LocalDate verdaderaFechaFin;
  private Long bandaId;
  private Long hermandadId;
}