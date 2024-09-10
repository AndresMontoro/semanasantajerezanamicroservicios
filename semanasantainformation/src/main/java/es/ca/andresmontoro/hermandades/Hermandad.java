package es.ca.andresmontoro.hermandades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.ca.andresmontoro.contratos.Contrato;
import es.ca.andresmontoro.validators.NoSpecialCharacters;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Hermandad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  public Long getId() { return id; }

  @NotNull(message = "El apodo no puede ser nulo")
  @NotEmpty(message = "El apodo no puede estar vacío")
  @Column(unique = true)
  @Size(max = 64, message = "El apodo no puede tener más de 64 caracteres")
  @NoSpecialCharacters
  private String apodo;
  public String getApodo() { return apodo; }
  public void setApodo(String apodo) { this.apodo = apodo; }

  @NotNull(message = "El nombre no puede ser nulo")
  @NotEmpty(message = "El nombre no puede estar vacío")
  @Column(unique = true)
  @Size(max = 1024, message = "El nombre no puede tener más de 1024 caracteres")
  @NoSpecialCharacters
  private String nombre;
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  @NotNull
  @NotEmpty
  private String historia;
  public String getHistoria() { return historia; }
  public void setHistoria(String historia) { this.historia = historia; }

  @NotNull
  @Range(min = 0)
  private Integer numeroNazarenos;
  public Integer getNumeroNazarenos() { return numeroNazarenos; }
  public void setNumeroNazarenos(Integer numeroNazarenos) {
    this.numeroNazarenos = numeroNazarenos;
  }

  @NotNull
  @Range(min = 0)
  private Integer numeroHermanos;
  public Integer getNumeroHermanos() { return numeroHermanos; }
  public void setNumeroHermanos(Integer numeroHermanos) {
    this.numeroHermanos = numeroHermanos;
  }

  @NotNull
  @PastOrPresent
  private LocalDate fundacion;
  public LocalDate getFundacion() { return fundacion; }
  public void setFundacion(LocalDate fundacion) {
    this.fundacion = fundacion;
  }

  @NotNull
  private WeekDay diaSalida;
  public WeekDay getDiaSalida() { return diaSalida; }
  public void setDiaSalida(WeekDay diaSalida) {
    this.diaSalida = diaSalida;
  }

  public Hermandad() { }

  public Hermandad(String apodo, String nombre, String historia, Integer numeroNazarenos,
    Integer numeroHermanos, LocalDate fundacion, WeekDay diaSalida) {
    this.setApodo(apodo);
    this.setNombre(nombre);
    this.setHistoria(historia);
    this.setNumeroNazarenos(numeroNazarenos);
    this.setNumeroHermanos(numeroHermanos);
    this.setFundacion(fundacion);
    this.setDiaSalida(diaSalida);
  }

  @OneToMany(mappedBy = "hermandad", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Contrato> contratos = new ArrayList<>();

  public List<Contrato> getContratos() {
    return contratos;
  }

  public void setContratos(List<Contrato> contratos) {
    this.contratos = contratos;
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
    Hermandad other = (Hermandad) obj;
    return Objects.equals(id, other.id);
  }
}