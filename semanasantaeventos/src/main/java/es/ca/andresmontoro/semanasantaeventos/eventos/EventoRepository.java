package es.ca.andresmontoro.semanasantaeventos.eventos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
  public Optional<Evento> findById(Long id);
  
  public Page<Evento> findAll(Pageable pageable);
}
