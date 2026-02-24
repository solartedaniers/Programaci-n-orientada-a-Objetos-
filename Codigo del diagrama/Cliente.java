public class Cliente {

    private int idCliente;
    private String nombre;
    private String correo;

    public Cliente(int idCliente, String nombre, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Pedido crearPedido() {
        return new Pedido(this);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Correo: " + correo + " | ID: " + idCliente;
    }
}
