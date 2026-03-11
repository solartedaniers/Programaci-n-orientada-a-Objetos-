package co.ucc.apipedidos.models;

import java.time.LocalDateTime;

/**
 * Clase abstracta que representa cualquier movimiento financiero.
 *
 * ABSTRACCIÓN: define el contrato común para Pago y Devolucion.
 * ENCAPSULAMIENTO: estado es protected; solo las subclases lo modifican
 *   dentro de su implementación de procesar(). Sin setter público.
 * POLIMORFISMO: procesar() abstracto — cada subclase define su comportamiento.
 */
public abstract class Transaccion {

    private final int idTransaccion;
    private final LocalDateTime fecha;
    protected double monto;
    protected String estado;
    protected final int idReferencia;

    protected Transaccion(int idTransaccion, double monto, int idReferencia) {
        this.idTransaccion = idTransaccion;
        this.monto         = monto;
        this.idReferencia  = idReferencia;
        this.estado        = "PENDIENTE";
        this.fecha         = LocalDateTime.now();
    }

    /** POLIMORFISMO de sobreescritura: cada subclase implementa su lógica. */
    public abstract void procesar();

    public int getIdTransaccion()   { return idTransaccion; }
    public double getMonto()        { return monto; }
    public String getEstado()       { return estado; }
    public LocalDateTime getFecha() { return fecha; }
    public int getIdReferencia()    { return idReferencia; }
}