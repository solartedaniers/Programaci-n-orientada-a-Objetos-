package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.MetodoPago;

/**
 * HERENCIA: Pago ES UNA Transaccion.
 * POLIMORFISMO de sobreescritura: implementa procesar() con lógica propia.
 */
public class Pago extends Transaccion {

    private final MetodoPago metodo;

    public Pago(int idTransaccion, double monto, MetodoPago metodo, int idPedido) {
        super(idTransaccion, monto, idPedido);
        this.metodo = metodo;
    }

    @Override
    public void procesar() {
        if ("PROCESADO".equals(this.estado)) {
            throw new IllegalStateException("El pago ya fue procesado.");
        }
        this.estado = "PROCESADO";
    }

    public MetodoPago getMetodo() { return metodo; }
}