package co.ucc.apipedidos.models;

/**
 * HERENCIA: EnvioExpres ES UN Envio.
 * POLIMORFISMO: implementa calcularCosto() con tarifa exprés.
 * Costo = max(peso, volumen) * 8000
 */
public class EnvioExpres extends Envio {

    private static final double TARIFA = 8000.0;

    public EnvioExpres(int idEnvio, double peso, double volumen, int idPedido) {
        super(idEnvio, peso, volumen, idPedido);
    }

    @Override
    public double calcularCosto() {
        return Math.max(peso, volumen) * TARIFA;
    }
}