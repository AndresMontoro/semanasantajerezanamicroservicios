package es.ca.andresmontoro.bandas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.ca.andresmontoro.contratos.Contrato;
import es.ca.andresmontoro.localizaciones.ciudades.Ciudad;
import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banda", uniqueConstraints = {
  @UniqueConstraint(columnNames = { "nombre", "estilo", "ciudad" })
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 128, message = "El nombre no puede tener más de 128 caracteres")
  @NoSpecialCharacters
  private String nombre;

  @NotNull(message = "La banda debe tener algún estilo")
  private Estilo_Banda estilo;

  @NotNull(message = "La banda debe tener un número aproximado de componentes")
  @Range(min = 0, message = "El número de componentes aproximado debe ser mayor o igual a 0")
  private Integer numeroComponentesAprox;

  @NotNull(message = "La banda debe tener una fecha de fundación")
  @PastOrPresent(message = "La fecha de fundación debe ser en el pasado o presente")
  private LocalDate fundacion;

  @ManyToOne
  @NotNull
  private Ciudad ciudad;

  @OneToMany(mappedBy = "banda", fetch = FetchType.LAZY)
  @JsonIgnore
  @Builder.Default
  private List<Contrato> contratos = new ArrayList<>(); 


  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public boolean equals(BandaDTO banda) {
    return Objects.equals(nombre, banda.getNombre())
      && Objects.equals(estilo, banda.getEstilo())
      && Objects.equals(numeroComponentesAprox, banda.getNumeroComponentesAprox())
      && Objects.equals(fundacion, banda.getFundacion())
      && Objects.equals(ciudad.getId(), banda.getCiudadId());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Banda other = (Banda) obj;
    return Objects.equals(id, other.id);
  }
}