package es.ca.andresmontoro.semanasantaeventos.TipoEventos;

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
public class TipoEventoResponse {
  private Long id;
  private String nombre;
}
