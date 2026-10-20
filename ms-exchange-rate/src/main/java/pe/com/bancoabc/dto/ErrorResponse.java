package pe.com.bancoabc.exchangerate.dto;

public record ErrorResponse(
        String codigo,
        String mensaje
) {}