package co.ucc.apipedidos.models;

import java.time.LocalDateTime;

/**
 * Clase abstracta que representa cualquier movimiento financiero del sistema.
 *
 * Aplica ABSTRACCIÓN: define el contrato común (monto, estado, fecha) y delega
 * la implementación específica de procesar() a las subclases (Pago, Devolucion).
 *
 * Aplica ENCAPSULAMIENTO Y OCULTAMIENTO: los atributos son protected para que
 * solo las subclases los modifiquen directamente; no se exponen setters públicos,
 * evitando que capas externas alteren el estado sin pasar por la lógica de negocio.
 */
public abstract class Transaccion {

    private final int idTransaccion;
    protected double monto;
    protected String estado;
    protected final LocalDateTime fecha;
    protected final int idReferencia; // idPedido u otro objeto de referencia

    protected Transaccion(int idTransaccion, double monto, int idReferencia) {
        validarMonto(monto);
        this.idTransaccion = idTransaccion;
        this.monto         = monto;
        this.idReferencia  = idReferencia;
        this.estado        = "PENDIENTE";
        this.fecha         = LocalDateTime.now();
    }

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException(
                "El monto debe ser mayor que cero. Recibido: " + monto);
        }
    }

    /**
     * Método abstracto que cada subclase implementa con su lógica propia.
     * POLIMORFISMO: se puede invocar sobre cualquier Transaccion sin conocer el tipo concreto.
     */
    public abstract void procesar();

    // ─── Getters (sin setters públicos: ocultamiento) ────────────────────────

    public int getIdTransaccion()      { return idTransaccion; }
    public double getMonto()           { return monto; }
    public String getEstado()          { return estado; }
    public LocalDateTime getFecha()    { return fecha; }
    public int getIdReferencia()       { return idReferencia; }
}