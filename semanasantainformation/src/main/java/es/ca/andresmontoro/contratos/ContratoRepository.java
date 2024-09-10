package es.ca.andresmontoro.contratos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.ca.andresmontoro.bandas.Banda;
import es.ca.andresmontoro.hermandades.Hermandad;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
  public Optional<Contrato> findById(Long id);

  public Optional<Contrato> findByFechaInicioAndFechaFinAndBandaAndHermandad(
    LocalDate fechaInicio,
    LocalDate fechaFin,
    Banda banda,
    Hermandad hermandad
  );

  public Page<Contrato> findAll(Pageable pageable);

  @Query(
    "SELECT new es.ca.andresmontoro.contratos.ContratoResponse(c.id, c.fechaInicio, c.fechaFin, c.verdaderaFechaFin, c.banda.Id, c.hermandad.Id)"
    + " FROM Contrato c"
    + " WHERE c.fechaInicio <= :endOfYear"
    + " AND c.fechaFin >= :beginOfYear"
    + " AND c.banda.ciudad.id = :ciudadId"
  )
  public List<ContratoResponse> getContratosAnnioCiudad(
    @Param("beginOfYear") LocalDate beginOfyear,
    @Param("endOfYear") LocalDate endOfYear,
    @Param("ciudadId") Long ciudadId
  );

  @Query(
    "SELECT new es.ca.andresmontoro.contratos.ContratoResponse(c.id, c.fechaInicio, c.fechaFin, c.verdaderaFechaFin, c.banda.Id, c.hermandad.Id)"
    + " FROM Contrato c"
    + " WHERE c.fechaInicio <= :endOfYear"
    + " AND c.fechaFin >= :beginOfYear"   // Puede haber contratos que terminen antes del inicio del annio
  )
  public List<ContratoResponse> findByAnnio(
    @Param("beginOfYear") LocalDate beginOfYear,
    @Param("endOfYear") LocalDate endOfYear
  );
}
