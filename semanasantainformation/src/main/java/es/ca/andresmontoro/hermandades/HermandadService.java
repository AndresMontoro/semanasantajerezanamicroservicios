package es.ca.andresmontoro.hermandades;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import es.ca.andresmontoro.validators.Validator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HermandadService {
  private final HermandadRepository hermandadRepository;

  public HermandadService(HermandadRepository hermandadRepository) {
    this.hermandadRepository = hermandadRepository;
  }

  public List<Hermandad> findAll() {
    return hermandadRepository.findAll();
  }

  public Page<Hermandad> findAll(Pageable pageable) {
    return hermandadRepository.findAll(pageable);
  }

  public Hermandad create(Hermandad hermandad) {
    return hermandadRepository.save(hermandad);
  }

  @Transactional
  public void removeById(Long id) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");

    if (findById(id).isEmpty())
      throw new EntityNotFoundException("Hermandad no encontrada");

    hermandadRepository.deleteById(id);
  }

  public Hermandad updateHermandad(Long id, Hermandad updateHermandad) {
    if(!Validator.isIdValid(id))
      throw new IllegalArgumentException("El id no puede ser nulo o menor que 1");
      
    Hermandad hermandad = findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Hermandad no encontrada"));

    if (hermandad.equals(updateHermandad))
      return hermandad;

    hermandad.setApodo(updateHermandad.getApodo());
    hermandad.setNombre(updateHermandad.getNombre());
    hermandad.setHistoria(updateHermandad.getHistoria());
    hermandad.setNumeroNazarenos(updateHermandad.getNumeroNazarenos());
    hermandad.setNumeroHermanos(updateHermandad.getNumeroHermanos());
    hermandad.setFundacion(updateHermandad.getFundacion());
    hermandad.setDiaSalida(updateHermandad.getDiaSalida());
    return create(hermandad);
  }

  public Optional<Hermandad> findById(Long id) {
    return hermandadRepository.findById(id);
  }

  @Async("asyncTaskExecutor")
  public CompletableFuture<Optional<Hermandad>> findByIdAsync(Long id) {
    return CompletableFuture.supplyAsync(() -> hermandadRepository.findById(id));
  }
}
