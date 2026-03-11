package co.ucc.apipedidos.models;

/**
 * HERENCIA: Devolucion ES UNA Transaccion.
 * POLIMORFISMO de sobreescritura: implementa procesar() con lógica propia.
 */
public class Devolucion extends Transaccion {

    private final String motivo;

    public Devolucion(int idTransaccion, double monto, int idPedido, String motivo) {
        super(idTransaccion, monto, idPedido);
        this.motivo = motivo;
    }

    @Override
    public void procesar() {
        if ("REEMBOLSADO".equals(this.estado)) {
            throw new IllegalStateException("La devolución ya fue reembolsada.");
        }
        this.estado = "REEMBOLSADO";
    }

    public String getMotivo() { return motivo; }
}