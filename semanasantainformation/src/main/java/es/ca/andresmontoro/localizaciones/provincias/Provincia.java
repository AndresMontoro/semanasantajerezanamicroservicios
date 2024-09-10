package es.ca.andresmontoro.localizaciones.provincias;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import es.ca.andresmontoro.localizaciones.ciudades.Ciudad;
import es.ca.andresmontoro.localizaciones.comunidades.ComunidadAutonoma;
import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Provincia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 64, message = "El nombre no puede tener más de 64 caracteres")
  @NoSpecialCharacters
  private String nombre;

  @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonBackReference
  @Builder.Default
  private Set<Ciudad> ciudades = new HashSet<>();

  @ManyToOne
  @JsonManagedReference
  @NotNull(message = "La comunidad autónoma no puede ser nula")
  private ComunidadAutonoma comunidadAutonoma;

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public boolean equals(ProvinciaDTO provincia) {
    return (
      Objects.equals(nombre, provincia.getNombre()) &&
      Objects.equals(comunidadAutonoma.getId(), provincia.getComunidadId())
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
    Provincia other = (Provincia) obj;
    return Objects.equals(id, other.id);
  }
}
