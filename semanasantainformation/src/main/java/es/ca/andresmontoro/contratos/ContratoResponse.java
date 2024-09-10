package es.ca.andresmontoro.contratos;

import java.time.LocalDate;

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
public class ContratoResponse {
  private Long id;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private LocalDate verdaderaFechaFin;
  private Long bandaId;
  private Long hermandadId;
}
