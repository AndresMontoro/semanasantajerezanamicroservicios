package es.ca.andresmontoro.hermandades;

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
@RequestMapping("/ssjerezana/informacion/hermandad")

public class HermandadController implements CrudController<Hermandad, Hermandad> {
  private final HermandadService hermandadService;

  public HermandadController(HermandadService hermandadService) {
    this.hermandadService = hermandadService;
  }

  @Override
  @GetMapping
  public Page<Hermandad> getAll(Pageable pageable) {
    return hermandadService.findAll(pageable);
  }

  @Override
  @PostMapping
  public ResponseEntity<Hermandad> create(@Valid @RequestBody Hermandad hermandad) {
    Hermandad newHermandad = hermandadService.create(hermandad);
    return new ResponseEntity<>(newHermandad, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Hermandad> delete(@PathVariable Long id) {
    hermandadService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Hermandad> update(@PathVariable Long id, @Valid @RequestBody Hermandad hermandad) {
    hermandadService.updateHermandad(id, hermandad);
    return new ResponseEntity<>(hermandad, HttpStatus.OK);
  }
}
