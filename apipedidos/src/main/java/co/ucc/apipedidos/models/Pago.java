package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.MetodoPago;

/**
 * Representa un pago realizado por un cliente para cubrir un pedido.
 *
 * HERENCIA: Extiende Transaccion ("Pago ES UNA Transaccion").
 * Reutiliza monto, estado, fecha e idReferencia del padre.
 *
 * POLIMORFISMO: implementa procesar() con lógica propia de pago.
 *
 * ENCAPSULAMIENTO: metodo es privado y solo se expone mediante getter.
 * El estado se actualiza únicamente a través de procesar(), nunca con setter externo.
 */
public class Pago extends Transaccion {

    private final MetodoPago metodo;

    public Pago(int idTransaccion, double monto, MetodoPago metodo, int idPedido) {
        super(idTransaccion, monto, idPedido);
        if (metodo == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo.");
        }
        this.metodo = metodo;
    }

    /**
     * Procesa el pago: valida que no esté ya procesado y actualiza el estado.
     * POLIMORFISMO: comportamiento específico de Pago.
     */
    @Override
    public void procesar() {
        if ("PROCESADO".equals(this.estado)) {
            throw new IllegalStateException("El pago ya fue procesado.");
        }
        this.estado = "PROCESADO";
    }

    // ─── Getter específico de Pago ────────────────────────────────────────────

    public MetodoPago getMetodo() { return metodo; }
}