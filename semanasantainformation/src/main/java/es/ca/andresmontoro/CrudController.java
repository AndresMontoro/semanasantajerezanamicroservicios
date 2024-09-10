package es.ca.andresmontoro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

public interface CrudController<R, DTO> {
  @GetMapping
  public Page<R> getAll(Pageable pageable);

  @PostMapping
  public ResponseEntity<R> create(@Valid @RequestBody DTO dto);

  @DeleteMapping("/{id}")
  public ResponseEntity<R> delete(@PathVariable Long id);

  @PutMapping("/{id}")
  public ResponseEntity<R> update(@PathVariable Long id, @Valid @RequestBody DTO dto);
}
