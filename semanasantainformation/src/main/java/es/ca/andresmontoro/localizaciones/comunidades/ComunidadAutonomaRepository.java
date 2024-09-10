package es.ca.andresmontoro.localizaciones.comunidades;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunidadAutonomaRepository extends JpaRepository<ComunidadAutonoma, Long> {
  Optional<ComunidadAutonoma> findById(Long id);
}
