package es.ca.andresmontoro.contratos;

import java.time.Year;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/contrato")
public class ContratoController {
  private final ContratoService contratoService;

  public ContratoController(ContratoService contratoService) {
    this.contratoService = contratoService;
  }

  @GetMapping
  public Page<ContratoResponse> getAll(Pageable pageable) {
    return contratoService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContratoResponse> getById(@PathVariable Long id) {
    Contrato contrato = contratoService.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Contrato no encontrado"));
    ContratoResponse contratoResponse = ContratoMapper.toResponse(contrato);
    return new ResponseEntity<>(contratoResponse, HttpStatus.OK);
  }

  @GetMapping("/annio/{annio}")
  public ResponseEntity<List<ContratoResponse>> getContratosAnnio(@PathVariable Year annio) {
    List<ContratoResponse> contratosAnnio = contratoService.getContratosAnnio(annio);
    return new ResponseEntity<>(contratosAnnio, HttpStatus.OK);
  }

  @GetMapping("/annioCiudad")
  public ResponseEntity<List<ContratoResponse>> getContratosPeriodoCiudad(
    @RequestParam(required = true) Year annio,
    @RequestParam(required = true) Long idCiudad
  ) {
    List<ContratoResponse> contratosAnnioCiudad = contratoService.getContratosAnnioCiudad(annio, idCiudad);
    return new ResponseEntity<>(contratosAnnioCiudad, HttpStatus.OK);
  }

  // @PostMapping()
  // public ResponseEntity<Contrato> create(@Valid @RequestBody ContratoDTO contrato) {
  //   Contrato newContrato = contratoService.create(contrato);
  //   return new ResponseEntity<>(newContrato, HttpStatus.CREATED);
  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<ContratoResponse> delete(@PathVariable Long id) {
    ContratoResponse contratoResponse = contratoService.removeById(id);
    return new ResponseEntity<>(contratoResponse, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ContratoResponse> update(@PathVariable Long id, @Valid @RequestBody ContratoDTO contrato) {
    ContratoResponse updatedContrato = contratoService.updateContrato(id, contrato);
    return new ResponseEntity<>(updatedContrato, HttpStatus.OK);
  }
}
