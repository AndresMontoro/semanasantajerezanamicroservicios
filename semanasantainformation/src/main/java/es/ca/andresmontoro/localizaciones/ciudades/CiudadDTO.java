package es.ca.andresmontoro.localizaciones.ciudades;

import org.hibernate.validator.constraints.Range;

import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CiudadDTO {
  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 64, message = "El nombre no puede tener más de 64 caracteres")
  @NoSpecialCharacters
  private String nombre;
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  @Range(min = 1, message = "El id de la provincia debe ser mayor que 0")
  private Long provinciaId;
  public Long getProvinciaId() { return provinciaId; }
  public void setProvinciaId(Long provinciaId) { 
    this.provinciaId = provinciaId; 
  }

  @Range(min = 1, message = "El id de la comunidad autónoma debe ser mayor que 0")
  private Long comunidadId;
  public Long getComunidadId() { return comunidadId; }
  public void setComunidadId(Long comunidadId) {
    this.comunidadId = comunidadId;
  }

  @AssertTrue(message = "El id de la provincia o de la comunidad autónoma debe ser nulo")
  public boolean isValid() {
    return (
      (this.provinciaId != null && this.comunidadId == null) ||
      (this.provinciaId == null && this.comunidadId != null)
    );
  }

  public CiudadDTO(Builder builder) {
    this.nombre = builder.nombre;
    this.provinciaId = builder.provinciaId;
    this.comunidadId = builder.comunidadId;
  }

  public static class Builder {
    private String nombre;
    private Long provinciaId;
    private Long comunidadId;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    public Builder withNombre(String nombre) {
      this.nombre = nombre;
      return this;
    }

    public Builder withProvinciaId(Long provinciaId) {
      this.provinciaId = provinciaId;
      return this;
    }

    public Builder withComunidadId(Long comunidadId) {
      this.comunidadId = comunidadId;
      return this;
    }

    public CiudadDTO build() {
      return new CiudadDTO(this);
    }
  } 
}
