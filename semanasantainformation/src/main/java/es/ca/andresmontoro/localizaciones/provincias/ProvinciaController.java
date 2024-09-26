package es.ca.andresmontoro.localizaciones.provincias;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.CrudController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ssjerezana/informacion/provincia")
public class ProvinciaController implements CrudController<ProvinciaResponse, ProvinciaDTO> {
  private final ProvinciaService provinciaService;

  public ProvinciaController(ProvinciaService provinciaService) {
    this.provinciaService = provinciaService;
  }

  @Override
  @GetMapping
  public Page<ProvinciaResponse> getAll(Pageable pageable) {
    return provinciaService.findAll(pageable);
  }

  @Override
  @PostMapping
  public ResponseEntity<ProvinciaResponse> create(ProvinciaDTO provincia) {
    ProvinciaResponse newProvincia = provinciaService.create(provincia);
    return new ResponseEntity<>(newProvincia, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<ProvinciaResponse> delete(Long id) {
    provinciaService.removeById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ProvinciaResponse> update(Long id, ProvinciaDTO provincia) {
    ProvinciaResponse updatedProvincia = provinciaService.update(id, provincia);
    return new ResponseEntity<>(updatedProvincia, HttpStatus.OK);
  }

  @GetMapping("/findByComunidad/{id}")
  public List<ProvinciaResponse> getByComunidad(@PathVariable("id") Integer id) {
    return this.provinciaService.findByComunidadAutonoma(id);
  }
}
