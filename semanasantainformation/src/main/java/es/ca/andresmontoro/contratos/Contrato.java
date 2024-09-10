package es.ca.andresmontoro.contratos;

import java.time.LocalDate;
import java.util.Objects;

import es.ca.andresmontoro.bandas.Banda;
import es.ca.andresmontoro.hermandades.Hermandad;
import es.ca.andresmontoro.validators.Validator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contrato", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "fechaInicio", "fechaFin", "banda_id", "hermandad_id" })
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull(message = "La fecha de inicio no puede ser nula")
  @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
  private LocalDate fechaInicio;

  @NotNull(message = "La fecha de fin no puede ser nula")
  @Future(message = "La fecha de fin debe ser en el futuro")
  private LocalDate fechaFin;

  @PastOrPresent(message = "La verdadera fecha de fin debe ser en el pasado o presente")
  private LocalDate verdaderaFechaFin;

  @NotNull(message = "La banda no puede ser nula")
  @ManyToOne
  private Banda banda;

  @NotNull(message = "La hermandad no puede ser nula")
  @ManyToOne
  @Valid
  private Hermandad hermandad;

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  // Uses Objects.equals() to compare the fields with no nullpointer exception
  public boolean equals(ContratoDTO contrato) {
    return Objects.equals(fechaInicio, contrato.getFechaInicio())
        && Objects.equals(fechaFin, contrato.getFechaFin())
        && Objects.equals(verdaderaFechaFin, contrato.getVerdaderaFechaFin())
        && Objects.equals(banda.getId(), contrato.getBandaId())
        && Objects.equals(hermandad.getId(), contrato.getHermandadId());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Contrato other = (Contrato) obj;
    return Objects.equals(id, other.id);
  }

  @AssertTrue(message = "La fecha de inicio debe ser antes que la fecha de fin y que la verdadera fecha fin")
  public boolean isValid() {
    return (
      Validator.isBefore(fechaInicio, fechaFin) &&
      Validator.isBefore(fechaInicio, verdaderaFechaFin)
    );
  }
}
