package es.ca.andresmontoro.semanasantaeventos.Eventos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
  Optional<Evento> findById(Long id);
}
