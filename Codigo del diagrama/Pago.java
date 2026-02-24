public class Pago {

    private MetodoPago metodo;
    private EstadoPago estado;

    public Pago(MetodoPago metodo) {
        this.metodo = metodo;
        this.estado = EstadoPago.PENDIENTE;
    }

    public void procesarPago() {
        estado = EstadoPago.APROBADO;
        System.out.println("Pago aprobado con " + metodo);
    }

    public EstadoPago getEstado() {
        return estado;
    }
}
