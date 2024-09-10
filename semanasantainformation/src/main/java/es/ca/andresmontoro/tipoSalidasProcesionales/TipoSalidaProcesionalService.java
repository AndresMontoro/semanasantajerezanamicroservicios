package es.ca.andresmontoro.tipoSalidasProcesionales;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TipoSalidaProcesionalService {
    private final TipoSalidaProcesionalRespository tipoSalidaProcesionalRespository;

    public TipoSalidaProcesionalService(TipoSalidaProcesionalRespository tipoSalidaProcesionalRespository) {
        this.tipoSalidaProcesionalRespository = tipoSalidaProcesionalRespository;
    }

    public Page<TipoSalidaProcesional> findAll(Pageable pageable) {
        return tipoSalidaProcesionalRespository.findAll(pageable);
    }

    public TipoSalidaProcesional create(TipoSalidaProcesional tipoSalidaProcesional) {
        return tipoSalidaProcesionalRespository.save(tipoSalidaProcesional);
    }

    @Transactional
    public void remove(TipoSalidaProcesional tipoSalidaProcesional) {
        tipoSalidaProcesionalRespository.delete(tipoSalidaProcesional);
    }

    @Transactional
    public void removeById(Long id) {
        tipoSalidaProcesionalRespository.deleteById(id);
    }

    public Optional<TipoSalidaProcesional> findById(Long id) {
        return tipoSalidaProcesionalRespository.findById(id);
    }

    public TipoSalidaProcesional update(TipoSalidaProcesional tipoSalidaProcesional) {
        TipoSalidaProcesional updateSalidaProcesional = findById(tipoSalidaProcesional.getId())
            .orElseThrow(() -> new EntityNotFoundException("Tipo de salida procesional no encontrado"));
        
        updateSalidaProcesional.setNombre(tipoSalidaProcesional.getNombre());
        return create(tipoSalidaProcesional);
    }

}
