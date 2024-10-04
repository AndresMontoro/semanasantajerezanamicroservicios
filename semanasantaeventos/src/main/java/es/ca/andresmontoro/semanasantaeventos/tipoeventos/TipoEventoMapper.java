package es.ca.andresmontoro.semanasantaeventos.tipoeventos;

public class TipoEventoMapper {
  public static TipoEventoResponse toResponse(TipoEvento tipoEvento) {
    return TipoEventoResponse.builder()
      .id(tipoEvento.getId())
      .nombre(tipoEvento.getNombre())
      .build();
  }

  public static TipoEvento toEntity(TipoEventoDTO tipoEventoDTO) {
    return TipoEvento.builder()
      .nombre(tipoEventoDTO.getNombre())
      .build();
  }
}
