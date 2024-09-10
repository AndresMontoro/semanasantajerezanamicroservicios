package es.ca.andresmontoro.salidasProcesionales;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class SalidaProcesionalDTO {
  @NotNull(message = "La fecha no puede ser nula")
  @Future(message = "La salida debe ser programada a posteriori")
  private LocalDate fecha;
  public LocalDate getFecha() { return fecha; }
  public void setFecha(LocalDate fecha) { this.fecha = fecha; }

  @NotNull(message = "El clima no puede ser nulo")
  private Clima clima;
  public Clima getClima() { return clima; }
  public void setClima(Clima clima) { this.clima = clima; }

  @NotNull(message = "El id de la hermandad no puede ser nulo")
  @Range(min = 1, message = "El id de la hermandad debe ser mayor que 0")
  private Long hermandadId;
  public Long getHermandadId() { return hermandadId; }
  public void setHermandadId(Long hermandadId) {
    this.hermandadId = hermandadId;
  }

  private Long contratoId;
  public Long getContratoId() { return contratoId; }
  public void setContratoId(Long contratoId) {
    this.contratoId = contratoId;
  }

  @NotNull(message = "El id del tipo de salida procesional no puede ser nulo")
  @Range(min = 1, message = "El id del tipo de salida procesional debe ser mayor que 0")
  private Long tipoId;
  public Long getTipoId() { return tipoId; }
  public void setTipoId(Long tipoId) { this.tipoId = tipoId; }
}
