package es.ca.andresmontoro.bandas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.ca.andresmontoro.localizaciones.ciudades.Ciudad;

public interface BandaRepository extends JpaRepository<Banda, Long> {
  public Optional<Banda> findById(Long id);

  public Page<Banda> findAll(Pageable pageable);

  public List<Banda> findByCiudad(Ciudad ciudad);
}
