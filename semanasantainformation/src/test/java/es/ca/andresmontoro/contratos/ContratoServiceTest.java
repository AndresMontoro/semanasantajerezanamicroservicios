// package es.ca.andresmontoro.contratos;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.quality.Strictness;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.junit.jupiter.MockitoSettings;

// import es.krait.ssjerez.bandas.Banda;
// import es.krait.ssjerez.hermandades.Hermandad;

// @ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
// public class ContratoServiceTest {
   
//     @Mock
//     private ContratoRepository contratoRepository;

//     @InjectMocks
//     private ContratoService contratoService;

//     private Contrato contrato;

//     @BeforeEach
//     void setUp() {
//         contrato = new Contrato();
//         contrato.setId(1L);
//         contrato.setFechaInicio(LocalDate.now());
//         contrato.setFechaFin(LocalDate.now().plusDays(1));
//         contrato.setBanda(new Banda());
//         contrato.setHermandad(new Hermandad());
//     }

//     @Test
//     void testCreate() {
//         when(this.contratoRepository.save(contrato)).thenReturn(contrato);

//         Contrato created = contratoService.create(contrato);

//         assertNotNull(created);
//         assertEquals(1L, created.getId());
//         verify(contratoRepository, times(1)).save(contrato);
//     }

//     @Test
//     void testFindAll() {
//         when(contratoRepository.findAll()).thenReturn(List.of(contrato));

//         List<Contrato> contratos = contratoService.findAll();

//         assertNotNull(contratos);
//         assertEquals(1, contratos.size());
//         verify(contratoRepository, times(1)).findAll();
//         assertEquals(contratos.get(0).getId(), contrato.getId());
//     }

//     @Test
//     void testRemoveById() {
//         when(contratoService.findById(1L)).thenReturn(Optional.of(contrato));

//         contratoService.removeById(1L);

//         verify(contratoRepository, times(1)).deleteById(1L);
//     }

//     @Test
//     void testRemoveByIdNotFound() {
//         when(contratoService.findById(1L)).thenReturn(Optional.empty());

//         try {
//             contratoService.removeById(1L);
//         } catch (Exception e) {
//             assertEquals("Contrato no encontrado", e.getMessage());
//         }
//     }

//     @Test
//     void testUpdateContrato() {
//         Contrato updateContrato = this.contrato;
//         updateContrato.setId(2L);
//         updateContrato.setFechaInicio(LocalDate.now().plusDays(1));
//         when(contratoService.findById(1L)).thenReturn(Optional.of(contrato));
//         when(contratoService.create(updateContrato)).thenReturn(updateContrato);
        
//         Contrato update = contratoService.updateContrato(1L, updateContrato);

//         assertNotNull(update);
//         assertEquals(update.getFechaInicio(), LocalDate.now().plusDays(1));
//     }
// }
