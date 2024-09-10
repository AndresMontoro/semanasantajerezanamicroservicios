package es.ca.andresmontoro.bandas;

public class BandaMapper {
  public static BandaResponse toResponse(Banda banda) {
    return BandaResponse.builder()
      .id(banda.getId())
      .nombre(banda.getNombre())
      .estilo(banda.getEstilo())
      .numeroComponentesAprox(banda.getNumeroComponentesAprox())
      .fundacion(banda.getFundacion())
      .ciudadId(banda.getCiudad().getId())
      .build();
  }
}
