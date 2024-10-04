package es.ca.andresmontoro.semanasantaeventos.eventos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ca.andresmontoro.semanasantaeventos.CrudController;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ssjerezana/eventos")
@AllArgsConstructor
public class EventoController implements CrudController<EventoResponse, EventoDTO> {
  private final EventoService eventoService;

  @Override
  public Page<EventoResponse> getAll(Pageable pageable) {
    return eventoService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventoResponse> getById(@PathVariable Long id) {
    Evento evento = eventoService.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
    EventoResponse eventoResponse = EventoMapper.toResponse(evento);
    return new ResponseEntity<>(eventoResponse, HttpStatus.OK);
  }
  
  @Override
  public ResponseEntity<EventoResponse> create(@Valid @RequestBody EventoDTO dto) {
    EventoResponse savedEvento = eventoService.create(dto);
    return new ResponseEntity<>(savedEvento, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<EventoResponse> delete(Long id) {
    EventoResponse deletedEvento = eventoService.removeById(id);
    return new ResponseEntity<>(deletedEvento, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<EventoResponse> update(Long id, @Valid EventoDTO dto) {
    EventoResponse updatedEvento = eventoService.update(id, dto);
    return new ResponseEntity<>(updatedEvento, HttpStatus.OK);
  }
}
