package pe.com.bancoabc.exchangerate.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import pe.com.bancoabc.exchangerate.dto.ErrorResponse;

@Provider
public class DailyLimitExceptionMapper
        implements ExceptionMapper<DailyLimitExceededException> {

    @Override
    public Response toResponse(
            DailyLimitExceededException exception) {

        ErrorResponse error = new ErrorResponse(
                "LIMITE_DIARIO_EXCEDIDO",
                exception.getMessage()
        );

        return Response.status(429)
                .entity(error)
                .build();
    }
}