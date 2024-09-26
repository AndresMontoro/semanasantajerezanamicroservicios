package es.ca.andresmontoro.localizaciones.comunidades;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.CrudController;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ssjerezana/informacion/comunidad-autonoma")
public class ComunidadAutonomaController implements CrudController<ComunidadAutonoma, ComunidadAutonoma> {
  private final ComunidadAutonomaService comunidadAutonomaService;

  public ComunidadAutonomaController(ComunidadAutonomaService comunidadAutonomaService) {
    this.comunidadAutonomaService = comunidadAutonomaService;
  }

  @Override
  @GetMapping
  public Page<ComunidadAutonoma> getAll(Pageable pageable) {
    return comunidadAutonomaService.findAll(pageable);
  }

  @Override
  @PostMapping
  public ResponseEntity<ComunidadAutonoma> create(@Valid ComunidadAutonoma comunidad) {
    ComunidadAutonoma newComunidadAutonoma = comunidadAutonomaService.create(comunidad);
    return new ResponseEntity<>(newComunidadAutonoma, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<ComunidadAutonoma> delete(Long id) {
    comunidadAutonomaService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ComunidadAutonoma> update(Long id, @Valid ComunidadAutonoma comunidad) {
    ComunidadAutonoma updatedComunidad = comunidadAutonomaService.update(id, comunidad);
    return new ResponseEntity<>(updatedComunidad, HttpStatus.OK);
  }
}
