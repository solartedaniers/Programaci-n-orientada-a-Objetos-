package co.ucc.apipedidos.models;

import java.time.LocalDateTime;

import co.ucc.apipedidos.models.enums.EstadoPago;
import co.ucc.apipedidos.models.enums.MetodoPago;

public class Pago {

    private final int idPago;
    private final double monto;
    private final LocalDateTime fecha;
    private final MetodoPago metodo;
    private final int idPedido;
    private EstadoPago estado;          // ← NO final, cambia con procesarPago()

    public Pago(int idPago, double monto, MetodoPago metodo, int idPedido) {
        validarMonto(monto);
        this.idPago   = idPago;
        this.monto    = monto;
        this.metodo   = metodo;
        this.idPedido = idPedido;
        this.estado   = EstadoPago.PENDIENTE;
        this.fecha    = LocalDateTime.now();
    }

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException(
                "El monto debe ser mayor que cero. Recibido: " + monto);
        }
    }

    public void procesarPago() {
        if (this.estado == EstadoPago.PROCESADO) {
            throw new IllegalStateException("El pago ya fue procesado.");
        }
        this.estado = EstadoPago.PROCESADO;
    }

    public int getIdPago()          { return idPago; }
    public double getMonto()        { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public EstadoPago getEstado()   { return estado; }
    public MetodoPago getMetodo()   { return metodo; }
    public int getIdPedido()        { return idPedido; }
}