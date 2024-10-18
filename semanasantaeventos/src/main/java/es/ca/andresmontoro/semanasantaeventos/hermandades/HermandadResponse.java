package es.ca.andresmontoro.semanasantaeventos.hermandades;

import java.time.LocalDate;

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
public class HermandadResponse {
  private Long id;

  private String apodo;

  private String nombre;

  private String historia;

  private Integer numeroNazarenos;

  private Integer numeroHermanos;

  private LocalDate fundacion;

  private WeekDay diaSalida;
}
