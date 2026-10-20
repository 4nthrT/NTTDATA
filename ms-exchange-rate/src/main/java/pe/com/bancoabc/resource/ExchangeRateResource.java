package pe.com.bancoabc.exchangerate.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import pe.com.bancoabc.exchangerate.dto.ErrorResponse;
import pe.com.bancoabc.exchangerate.dto.ExchangeRateResponse;
import pe.com.bancoabc.exchangerate.service.ExchangeRateService;

@Path("/api/v1/exchange-rates")
@Produces(MediaType.APPLICATION_JSON)
public class ExchangeRateResource {

    @Inject
    ExchangeRateService service;

    @GET
    public Response getExchangeRate(
            @QueryParam("dni") String dni) {

        if (dni == null || !dni.matches("\\d{8}")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(
                            "DNI_INVALIDO",
                            "Debe proporcionar un DNI de 8 dígitos"
                    ))
                    .build();
        }

        ExchangeRateResponse response =
                new ExchangeRateResponse(
                        dni,
                        service.getRate(dni)
                );

        return Response.ok(response).build();
    }
}