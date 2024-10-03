package es.ca.andresmontoro.semanasantaeventos.TipoEventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.semanasantaeventos.CrudController;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ssjerezana/eventos/tipo")
@AllArgsConstructor
public class TipoEventoController implements CrudController<TipoEventoResponse, TipoEventoDTO>{

  private final TipoEventoService tipoEventoService;

  @Override
  public Page<TipoEventoResponse> getAll(Pageable pageable) {
    return tipoEventoService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TipoEventoResponse> getById(Long id) {
    TipoEvento tipoEvento = tipoEventoService.findById(id)
      .orElseThrow(() -> new EntityNotFoundException());

    TipoEventoResponse tipoEventoResponse = TipoEventoMapper.toResponse(tipoEvento);
    return new ResponseEntity<>(tipoEventoResponse, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TipoEventoResponse> create(@Valid TipoEventoDTO dto) {
    TipoEventoResponse createdTipoEventoResponse = tipoEventoService.create(dto);
    return new ResponseEntity<>(createdTipoEventoResponse, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<TipoEventoResponse> delete(Long id) {
    TipoEventoResponse deletedTipoEvento = tipoEventoService.removeById(id);
    return new ResponseEntity<>(deletedTipoEvento, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<TipoEventoResponse> update(Long id, @Valid TipoEventoDTO dto) {
    TipoEventoResponse updatedTipoEvento = tipoEventoService.update(id, dto);
    return new ResponseEntity<>(updatedTipoEvento, HttpStatus.OK);
  }  
}
