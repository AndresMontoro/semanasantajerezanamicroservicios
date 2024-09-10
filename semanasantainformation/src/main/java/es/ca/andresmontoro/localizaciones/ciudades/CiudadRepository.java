package es.ca.andresmontoro.localizaciones.ciudades;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
  @Query("SELECT c FROM Ciudad c WHERE c.provincia.id = :provinciaId")
  List<Ciudad> findByProvincia(@Param("provinciaId") Integer provinciaId);

  Page<Ciudad> findAll(Pageable pageable);

  @Query("SELECT c FROM Ciudad c WHERE TRIM(c.nombre) = TRIM(:nombre)")
  Optional<Ciudad> findByNombreTrimmed(@Param("nombre") String nombre);
}
