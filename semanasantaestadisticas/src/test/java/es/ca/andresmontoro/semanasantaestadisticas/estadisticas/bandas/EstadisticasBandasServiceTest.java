// package es.ca.andresmontoro.semanasantaestadisticas.estadisticas.bandas;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import es.ca.andresmontoro.semanasantaestadisticas.bandas.BandaService;
// import es.ca.andresmontoro.semanasantaestadisticas.contratos.ContratoService;
// import reactor.core.publisher.Flux;
// import reactor.test.StepVerifier;

// import java.time.Year;

// public class EstadisticasBandasServiceTest {
  
//   @Autowired
//   private EstadisticasBandasService estadisticasBandasService;

//   @MockBean
//   private ContratoService contratoService;

//   @MockBean
//   private BandaService bandaService;

//   @Test
//   void testGetMostCommonBandaOnPeriod_NoContratos() {
//     int lastNYears = 3;

//     when(contratoService.getContratosMostCommonBandaOnYear(any(Year.class)))
//       .thenReturn(Flux.empty());

//     StepVerifier.create(estadisticasBandasService.getMostCommonBandaOnPeriod(lastNYears))
//       .expectNextCount(0) // No debe emitir ningún valor porque no hay contratos
//       .verifyComplete();

//     verify(
//       contratoService, 
//       times(3))
//         .getContratosMostCommonBandaOnYear(any(Year.class)
//     );
//   }
// }
