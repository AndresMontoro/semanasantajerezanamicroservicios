package es.ca.andresmontoro.salidasProcesionales;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalidaProcesionalRepository extends JpaRepository<SalidaProcesional, Long> {
  public Optional<SalidaProcesional> findById(Long id);

  public Page<SalidaProcesional> findAll(Pageable pageable);

  public void deleteById(Long id);
}
