package es.ca.andresmontoro.localizaciones.provincias;

public class ProvinciaMapper {
  public static ProvinciaResponse toResponse(Provincia provincia) {
    return ProvinciaResponse.builder()
      .id(provincia.getId())
      .nombre(provincia.getNombre())
      .comunidadId(provincia.getComunidadAutonoma().getId())
      .build();
  }
}
