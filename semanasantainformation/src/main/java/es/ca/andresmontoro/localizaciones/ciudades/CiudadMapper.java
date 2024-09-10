package es.ca.andresmontoro.localizaciones.ciudades;

public class CiudadMapper {
  public static CiudadResponse toResponse(Ciudad ciudad) {
    return CiudadResponse.builder()
      .id(ciudad.getId())
      .nombre(ciudad.getNombre())
      .provinciaId(
        (ciudad.getProvincia() != null)
          ? ciudad.getProvincia().getId()
          : null
      )
      .comunidadId(
        (ciudad.getComunidad() != null)
          ? ciudad.getComunidad().getId()
          : null
      )
      .build();
  }
}
