package es.ca.andresmontoro.semanasantaeventos.eventos;

import es.ca.andresmontoro.semanasantaeventos.tipoeventos.TipoEvento;

public class EventoMapper {
  public static EventoResponse toResponse(Evento evento) {
    return EventoResponse.builder()
      .id(evento.getId())
      .nombre(evento.getNombre())
      .description(evento.getDescription())
      .fechaYHora(evento.getFechaYHora())
      .idHermandad(evento.getIdHermandad())
      .idTipoEvento(evento.getTipoEvento().getId())
      .build();
  }

  public static Evento toEntity(EventoDTO eventoDTO, TipoEvento tipoEvento) {
    return Evento.builder()
      .nombre(eventoDTO.getNombre())
      .description(eventoDTO.getDescription())
      .fechaYHora(eventoDTO.getFechaYHora())
      .idHermandad(eventoDTO.getIdHermandad())
      .tipoEvento(tipoEvento)
      .build();
  }
}
