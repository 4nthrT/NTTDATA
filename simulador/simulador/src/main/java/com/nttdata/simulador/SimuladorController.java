package com.nttdata.simulador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulaciones")
public class SimuladorController {

    private static final double TASA_INTERES = 0.10;

    @PostMapping
    public ResponseEntity<SimulacionResponse> simular(
            @RequestBody SimulacionRequest request) {

        // R1: el monto debe estar entre 500 y 50000.
        if (request.getMonto() < 500
                || request.getMonto() > 50000) {
            return ResponseEntity.badRequest().build();
        }

        // R2: las cuotas deben estar entre 1 y 24.
        if (request.getCuotas() < 1
                || request.getCuotas() > 24) {
            return ResponseEntity.badRequest().build();
        }

        // R3: el total incluye el 10 % de interés.
        double totalPagar =
                request.getMonto() * (1 + TASA_INTERES);

        // R4: la cuota mensual se redondea a dos decimales.
        double cuotaMensual =
                Math.round(
                        totalPagar / request.getCuotas() * 100
                ) / 100.0;

        SimulacionResponse response = new SimulacionResponse(
                request.getMonto(),
                request.getCuotas(),
                TASA_INTERES,
                totalPagar,
                cuotaMensual
        );

        return ResponseEntity.ok(response);
    }
}