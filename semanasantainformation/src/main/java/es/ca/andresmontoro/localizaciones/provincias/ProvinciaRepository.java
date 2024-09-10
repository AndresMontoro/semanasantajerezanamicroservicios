package es.ca.andresmontoro.localizaciones.provincias;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
  Page<Provincia> findAll(Pageable pageable);

  Optional<Provincia> findById(Long id);
  
  @Query("SELECT p FROM Provincia p WHERE p.comunidadAutonoma.id = :comunidadAutonomaId")
  List<Provincia> findByComunidadAutonoma(@Param("comunidadAutonomaId") Integer comunidadAutonomaId);
}