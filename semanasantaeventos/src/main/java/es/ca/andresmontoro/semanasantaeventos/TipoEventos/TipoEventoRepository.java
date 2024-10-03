package es.ca.andresmontoro.semanasantaeventos.TipoEventos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long>{
  Optional<TipoEvento> findById(Long id);
  Optional<TipoEvento> findByNombre(String nombre);
}
