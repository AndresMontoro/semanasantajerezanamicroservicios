package es.ca.andresmontoro.semanasantaeventos.Eventos;

import java.time.LocalDateTime;

import es.ca.andresmontoro.semanasantaeventos.TipoEventos.TipoEvento;
import es.ca.andresmontoro.semanasantaeventos.validators.NoSpecialCharacters;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Evento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "El nombre del evento no puede ser nulo")
  @Size(min = 1, max = 128, message = "El nombre del evento debe tener entre 1 y 100 caracteres")
  @NoSpecialCharacters(message = "El nombre del evento no puede contener caracteres especiales")
  private String nombre;

  @NotNull(message = "La descripción del evento no puede ser nula")
  @Size(min = 1, max = 1024, message = "La descripción del evento debe tener entre 1 y 1024 caracteres")
  @NoSpecialCharacters(message = "El nombre del evento no puede contener caracteres especiales")
  private String description;

  @NotNull(message = "La fecha y hora del evento no puede ser nula")
  private LocalDateTime fechaYHora;

  @NotNull(message = "La duración del evento no puede ser nula")
  private Long idHermandad;

  @ManyToOne
  @NotNull(message = "El tipo de evento no puede ser nulo")
  private TipoEvento tipoEvento;
}
