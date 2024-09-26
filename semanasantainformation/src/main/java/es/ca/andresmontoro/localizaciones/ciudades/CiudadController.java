package es.ca.andresmontoro.localizaciones.ciudades;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.CrudController;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ssjerezana/informacion/ciudad")
public class CiudadController implements CrudController<CiudadResponse, CiudadDTO> {
  private final CiudadService ciudadService;

  public CiudadController(CiudadService ciudadService) {
    this.ciudadService = ciudadService;
  }

  @Override
  @GetMapping
  public Page<CiudadResponse> getAll(Pageable pageable) {
    return ciudadService.findAll(pageable);
  }

  @Override
  @PostMapping
  public ResponseEntity<CiudadResponse> create(@Valid CiudadDTO dto) {
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<CiudadResponse> delete(Long id) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<CiudadResponse> update(Long id, @Valid CiudadDTO dto) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @GetMapping("/findByName")
  public ResponseEntity<CiudadResponse> findByName(
    @RequestParam(required = true) String nombre
  ) {
    CiudadResponse ciudad = ciudadService.findByName(nombre);
    return new ResponseEntity<>(ciudad, HttpStatus.OK);
  }

  @GetMapping("/findByProvincia/{id}")
  public List<CiudadResponse> getCiudadesByProvincia(@PathVariable("id") Integer id) {
    return this.ciudadService.findByProvincia(id);
  }
}
