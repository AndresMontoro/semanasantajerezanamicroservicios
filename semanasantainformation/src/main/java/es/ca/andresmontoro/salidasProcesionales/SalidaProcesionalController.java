package es.ca.andresmontoro.salidasProcesionales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.CrudController;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/salidaProcesional")
public class SalidaProcesionalController implements CrudController<SalidaProcesional, SalidaProcesionalDTO> {

  private final SalidaProcesionalService salidaProcesionalService;

  public SalidaProcesionalController(SalidaProcesionalService salidaProcesionalService) {
    this.salidaProcesionalService = salidaProcesionalService;
  }

  @GetMapping
  public Page<SalidaProcesional> getAll(Pageable pageable) {
    return salidaProcesionalService.findAll(pageable);
  }

  @PostMapping
  public ResponseEntity<SalidaProcesional> create(@Valid @RequestBody SalidaProcesionalDTO salidaProcesional) {
    SalidaProcesional newSalidaProcesional = salidaProcesionalService.create(salidaProcesional);
    return new ResponseEntity<>(newSalidaProcesional, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SalidaProcesional> delete(@PathVariable Long id) {
    salidaProcesionalService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SalidaProcesional> update(
    @PathVariable Long id, 
    @Valid @RequestBody SalidaProcesionalDTO salidaProcesional
  ) {
    SalidaProcesional salidaProcesionalUpdated = 
      salidaProcesionalService.updateSalidaProcesional(id, salidaProcesional);
    return new ResponseEntity<>(salidaProcesionalUpdated, HttpStatus.OK);
  }
}
