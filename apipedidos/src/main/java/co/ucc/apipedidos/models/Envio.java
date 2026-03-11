package co.ucc.apipedidos.models;

/**
 * Clase abstracta que representa un envío asociado a un pedido.
 *
 * ABSTRACCIÓN: define el contrato calcularCosto() para todos los tipos de envío.
 *   No se puede instanciar directamente; solo a través de sus subclases.
 *
 * ENCAPSULAMIENTO:
 *   - idEnvio e idPedido son private final: inmutables.
 *   - peso y volumen son protected: las subclases los leen en calcularCosto().
 *
 * HERENCIA: EnvioEstandar, EnvioExpres, EnvioInternacional, EnvioPorDron
 *   extienden esta clase y SON UN Envio.
 *
 * POLIMORFISMO: al invocar calcularCosto() desde una referencia Envio,
 *   el método ejecutado depende del tipo concreto del objeto.
 */
public abstract class Envio {

    private final int idEnvio;
    private final int idPedido;
    protected final double peso;
    protected final double volumen;

    protected Envio(int idEnvio, double peso, double volumen, int idPedido) {
        this.idEnvio  = idEnvio;
        this.peso     = peso;
        this.volumen  = volumen;
        this.idPedido = idPedido;
    }

    /**
     * Calcula el costo del envío.
     * POLIMORFISMO de sobreescritura: cada subclase aplica su tarifa.
     * Fórmula base: max(peso, volumen) * tarifa_propia
     */
    public abstract double calcularCosto();

    public int getIdEnvio()    { return idEnvio; }
    public double getPeso()    { return peso; }
    public double getVolumen() { return volumen; }
    public int getIdPedido()   { return idPedido; }
}