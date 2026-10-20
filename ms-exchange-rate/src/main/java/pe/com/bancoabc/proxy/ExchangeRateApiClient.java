package pe.com.bancoabc.exchangerate.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/tipo-cambio")
@RegisterRestClient(configKey = "exchange-rate-api")
public interface ExchangeRateApiClient {

    @GET
    @Path("/today.json")
    ExchangeRateProxy getTodayRate();
}