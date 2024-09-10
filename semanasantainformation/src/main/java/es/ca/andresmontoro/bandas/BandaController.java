package es.ca.andresmontoro.bandas;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.CrudController;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/banda")
public class BandaController implements CrudController<BandaResponse, BandaDTO> {
  private final BandaService bandaService;

  public BandaController(BandaService bandaService) {
    this.bandaService = bandaService;
  }

  @Override
  @GetMapping
  public Page<BandaResponse> getAll(Pageable pageable) {
    return bandaService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BandaResponse> getById(@PathVariable Long id) {
    BandaResponse banda = bandaService.findByIdAndReturnBandaResponse(id);
    return new ResponseEntity<>(banda, HttpStatus.OK);
  }

  @Override
  @PostMapping
  public ResponseEntity<BandaResponse> create(@Valid @RequestBody BandaDTO banda) {
    BandaResponse newBanda = bandaService.create(banda);
    return new ResponseEntity<>(newBanda, HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<BandaResponse> delete(@PathVariable Long id) {
    BandaResponse bandaResponse = bandaService.removeById(id);
    return new ResponseEntity<>(bandaResponse, HttpStatus.OK);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<BandaResponse> update(@PathVariable Long id, @Valid @RequestBody BandaDTO banda) {
    BandaResponse updatedBanda = bandaService.updateBanda(id, banda);
    return new ResponseEntity<>(updatedBanda, HttpStatus.OK);
  }

  @GetMapping("/findByCiudad/{idCiudad}")
  public ResponseEntity<List<BandaResponse>> getBandasByCiudad(@PathVariable Long idCiudad) {
    List<BandaResponse> bandas = bandaService.findByCiudad(idCiudad);
    return new ResponseEntity<>(bandas, HttpStatus.OK);
  }
}
