package es.ca.andresmontoro.localizaciones.ciudades;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonoma;
import es.ca.andresmontoro.localizaciones.provincias.Provincia;
import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ciudad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 64, message = "El nombre no puede tener más de 64 caracteres")
  @NoSpecialCharacters
  private String nombre;

  @ManyToOne()
  @JsonManagedReference
  private Provincia provincia;

  @ManyToOne()
  @JsonManagedReference
  private ComunidadAutonoma comunidad;

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public boolean equals(CiudadDTO ciudad) {
    return (
      Objects.equals(nombre, ciudad.getNombre()) &&
      Objects.equals(provincia.getId(), ciudad.getProvinciaId()) &&
      Objects.equals(comunidad.getId(), ciudad.getComunidadId())
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Ciudad other = (Ciudad) obj;
    return Objects.equals(id, other.id);
  }

  @AssertTrue(message = "La ciudad debe pertenecer a una provincia o comunidad autónoma")
  public boolean isValid() {
    return ((this.provincia != null && this.comunidad == null) ||
      (this.provincia == null && this.comunidad != null));
  }
}