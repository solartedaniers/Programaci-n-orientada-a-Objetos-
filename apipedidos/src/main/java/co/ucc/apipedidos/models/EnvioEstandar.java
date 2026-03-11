package co.ucc.apipedidos.models;

/**
 * HERENCIA: EnvioEstandar ES UN Envio.
 * POLIMORFISMO: implementa calcularCosto() con tarifa estándar.
 * Costo = max(peso, volumen) * 5000
 */
public class EnvioEstandar extends Envio {

    private static final double TARIFA = 5000.0;

    public EnvioEstandar(int idEnvio, double peso, double volumen, int idPedido) {
        super(idEnvio, peso, volumen, idPedido);
    }

    @Override
    public double calcularCosto() {
        return Math.max(peso, volumen) * TARIFA;
    }
}