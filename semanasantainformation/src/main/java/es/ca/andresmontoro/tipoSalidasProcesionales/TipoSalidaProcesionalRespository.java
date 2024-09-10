package es.ca.andresmontoro.tipoSalidasProcesionales;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSalidaProcesionalRespository extends JpaRepository<TipoSalidaProcesional, Long> {
    Page<TipoSalidaProcesional> findAll(Pageable pageable);
    Optional<TipoSalidaProcesional> findById(Long id);
    void deleteById(Long id);
}
