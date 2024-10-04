package es.ca.andresmontoro.semanasantaeventos.eventos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventoResponse {
  private Long id;
  private String nombre;
  private String description;
  private LocalDateTime fechaYHora;
  private Long idHermandad;
  private Long idTipoEvento;
}
