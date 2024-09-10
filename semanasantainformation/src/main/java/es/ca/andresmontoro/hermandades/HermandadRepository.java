package es.ca.andresmontoro.hermandades;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HermandadRepository extends JpaRepository<Hermandad, Long> {
  public Page<Hermandad> findAll(Pageable pageable);

  public Optional<Hermandad> findById(Long id);

  public List<Hermandad> findByDiaSalida(WeekDay diaSalida);

  public boolean existsByApodo(String apodo);

  public void deleteById(Long id);
}
