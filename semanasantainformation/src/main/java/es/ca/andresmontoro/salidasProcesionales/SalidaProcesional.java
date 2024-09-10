package es.ca.andresmontoro.salidasProcesionales;

import java.time.LocalDate;
import java.util.Objects;

import es.ca.andresmontoro.contratos.Contrato;
import es.ca.andresmontoro.hermandades.Hermandad;
import es.ca.andresmontoro.tipoSalidasProcesionales.TipoSalidaProcesional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
public class SalidaProcesional {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  @NotNull(message = "La fecha no puede ser nula")
  @Future(message = "La salida debe ser programada a posteriori")
  private LocalDate fecha;

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  @NotNull(message = "El clima no puede ser nulo")
  private Clima clima;

  public Clima getClima() {
    return clima;
  }

  public void setClima(Clima clima) {
    this.clima = clima;
  }

  @ManyToOne
  @NotNull(message = "La hermandad no puede ser nula")
  private Hermandad hermandad;

  public Hermandad getHermandad() {
    return hermandad;
  }

  public void setHermandad(Hermandad hermandad) {
    this.hermandad = hermandad;
  }

  @ManyToOne
  private Contrato contrato;

  public Contrato getContrato() {
    return contrato;
  }

  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }

  @ManyToOne
  @NotNull(message = "El tipo de salida procesional no puede ser nulo")
  private TipoSalidaProcesional tipo;

  public TipoSalidaProcesional getTipo() {
    return tipo;
  }

  public void setTipo(TipoSalidaProcesional tipo) {
    this.tipo = tipo;
  }

  public SalidaProcesional() {
  }

  public SalidaProcesional(Builder builder) {
    this.fecha = builder.fecha;
    this.clima = builder.clima;
    this.hermandad = builder.hermandad;
    this.contrato = builder.contrato;
    this.tipo = builder.tipo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SalidaProcesional other = (SalidaProcesional) obj;
    return Objects.equals(id, other.id);
  }

  public static class Builder {
    private LocalDate fecha;
    private Clima clima;
    private Hermandad hermandad;
    private Contrato contrato;
    private TipoSalidaProcesional tipo;

    public Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder withFecha(LocalDate fecha) {
      this.fecha = fecha;
      return this;
    }

    public Builder withClima(Clima clima) {
      this.clima = clima;
      return this;
    }

    public Builder withHermandad(Hermandad hermandad) {
      this.hermandad = hermandad;
      return this;
    }

    public Builder withContrato(Contrato contrato) {
      this.contrato = contrato;
      return this;
    }

    public Builder withTipo(TipoSalidaProcesional tipo) {
      this.tipo = tipo;
      return this;
    }

    public SalidaProcesional build() {
      return new SalidaProcesional(this);
    }
  }
}
