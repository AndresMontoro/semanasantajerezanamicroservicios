// package es.ca.andresmontoro.semanasantaeventos;

// import org.springframework.boot.CommandLineRunner;

// // import java.time.LocalDateTime;

// import org.springframework.stereotype.Component;

// // import es.ca.andresmontoro.semanasantaeventos.Eventos.Evento;
// // import es.ca.andresmontoro.semanasantaeventos.Eventos.EventoRepository;
// import es.ca.andresmontoro.semanasantaeventos.TipoEventos.TipoEvento;
// import es.ca.andresmontoro.semanasantaeventos.TipoEventos.TipoEventoRepository;
// import lombok.AllArgsConstructor;

// @Component
// @AllArgsConstructor 
// public class DataLoader implements CommandLineRunner {
//   // private final EventoRepository eventoRepository;
//   private final TipoEventoRepository tipoEventoRepository;

//   public void run(String... args) throws Exception {
//     createTipoEvento();
//     // createEventos();
//   }

//   public void createTipoEvento() {
//     System.out.println("Creando tipo de evento Extraordinaria");

//     TipoEvento tipoEvento1 = TipoEvento.builder()
//       .nombre("Extraordinaria")
//       .build();

//     tipoEventoRepository.save(tipoEvento1);

//     TipoEvento tipoEventoRecuperado = tipoEventoRepository
//       .findByNombre("Extraordinaria")
//       .orElse(null);

//     System.out.println("Evento recuperado: " + tipoEventoRecuperado.getNombre());
//   }

//   // public void createEventos() {
  
//   // } 
// }
