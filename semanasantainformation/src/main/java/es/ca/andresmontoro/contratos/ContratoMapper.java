package es.ca.andresmontoro.contratos;

public class ContratoMapper {
  public static ContratoDTO toDTO(Contrato contrato) {
    return ContratoDTO.builder()
      .fechaInicio(contrato.getFechaInicio())
      .fechaFin(contrato.getFechaFin())
      .verdaderaFechaFin(contrato.getVerdaderaFechaFin())
      .bandaId(contrato.getBanda().getId())
      .hermandadId(contrato.getHermandad().getId())
      .build();
  }

  public static ContratoResponse toResponse(Contrato contrato) {
    return ContratoResponse.builder()
      .id(contrato.getId())
      .fechaInicio(contrato.getFechaInicio())
      .fechaFin(contrato.getFechaFin())
      .verdaderaFechaFin(contrato.getVerdaderaFechaFin())
      .bandaId(contrato.getBanda().getId())
      .hermandadId(contrato.getHermandad().getId())
      .build();
  }
}
