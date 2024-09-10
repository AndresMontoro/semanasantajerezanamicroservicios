package es.ca.andresmontoro.bandas;

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
public class BandaResponse {
  private Long id;
  private String nombre;
  private Estilo_Banda estilo;
  private Integer numeroComponentesAprox;
  private LocalDate fundacion;
  private Long ciudadId;
}
