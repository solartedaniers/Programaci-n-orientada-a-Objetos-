package co.ucc.apipedidos.models;

/**
 * HERENCIA: EnvioInternacional ES UN Envio.
 * POLIMORFISMO: implementa calcularCosto() con tarifa internacional.
 * Costo = max(peso, volumen) * 15000
 */
public class EnvioInternacional extends Envio {

    private static final double TARIFA = 15000.0;

    public EnvioInternacional(int idEnvio, double peso, double volumen, int idPedido) {
        super(idEnvio, peso, volumen, idPedido);
    }

    @Override
    public double calcularCosto() {
        return Math.max(peso, volumen) * TARIFA;
    }
}