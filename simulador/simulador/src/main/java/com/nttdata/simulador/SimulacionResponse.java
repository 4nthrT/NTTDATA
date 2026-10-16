package com.nttdata.simulador;

public class SimulacionResponse {

    private double monto;
    private int cuotas;
    private double tasaInteres;
    private double totalPagar;
    private double cuotaMensual;

    public SimulacionResponse(
            double monto,
            int cuotas,
            double tasaInteres,
            double totalPagar,
            double cuotaMensual) {

        this.monto = monto;
        this.cuotas = cuotas;
        this.tasaInteres = tasaInteres;
        this.totalPagar = totalPagar;
        this.cuotaMensual = cuotaMensual;
    }

    public double getMonto() {
        return monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }
}
