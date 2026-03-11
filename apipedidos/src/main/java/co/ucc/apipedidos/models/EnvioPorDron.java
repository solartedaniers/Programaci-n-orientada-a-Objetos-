package co.ucc.apipedidos.models;

/**
 * HERENCIA: EnvioPorDron ES UN Envio.
 * POLIMORFISMO: implementa calcularCosto() con tarifa por dron.
 * Costo = max(peso, volumen) * 20000
 */
public class EnvioPorDron extends Envio {

    private static final double TARIFA = 20000.0;

    public EnvioPorDron(int idEnvio, double peso, double volumen, int idPedido) {
        super(idEnvio, peso, volumen, idPedido);
    }

    @Override
    public double calcularCosto() {
        return Math.max(peso, volumen) * TARIFA;
    }
}