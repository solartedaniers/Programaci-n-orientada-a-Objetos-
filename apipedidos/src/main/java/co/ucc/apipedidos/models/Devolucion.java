package co.ucc.apipedidos.models;

/**
 * Representa una devolución de dinero asociada a un pedido previo.
 *
 * HERENCIA: Extiende Transaccion ("Devolucion ES UNA Transaccion").
 * Reutiliza monto, estado, fecha e idReferencia del padre.
 *
 * POLIMORFISMO: implementa procesar() con lógica propia de devolución.
 *
 * ENCAPSULAMIENTO: motivo es privado; el estado solo cambia mediante procesar().
 */
public class Devolucion extends Transaccion {

    private final String motivo;

    public Devolucion(int idTransaccion, double monto, int idPedido, String motivo) {
        super(idTransaccion, monto, idPedido);
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de la devolución no puede estar vacío.");
        }
        this.motivo = motivo;
    }

    /**
     * Procesa la devolución: valida que no esté ya reembolsada y actualiza estado.
     * POLIMORFISMO: comportamiento específico de Devolucion.
     */
    @Override
    public void procesar() {
        if ("REEMBOLSADO".equals(this.estado)) {
            throw new IllegalStateException("La devolución ya fue reembolsada.");
        }
        this.estado = "REEMBOLSADO";
    }

    // ─── Getter específico de Devolucion ─────────────────────────────────────

    public String getMotivo() { return motivo; }
}