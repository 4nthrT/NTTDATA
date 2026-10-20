package pe.com.bancoabc.exchangerate.dto;

import pe.com.bancoabc.exchangerate.proxy.ExchangeRateProxy;

public record ExchangeRateResponse(
        String dni,
        ExchangeRateProxy tipoCambio
) {}