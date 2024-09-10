package es.ca.andresmontoro;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

public interface CrudService<T, DTO> {
  public Page<T> findAll(Pageable pageable);

  public T create(DTO dto);

  public void remove(T entity);

  @Transactional
  public void removeById(Long id);

  public T update(Long id, DTO dto);

  public Optional<T> findById(Long id);
}
