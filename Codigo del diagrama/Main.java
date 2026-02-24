public class Main {

    public static void main(String[] args) {

        Producto laptop = new Producto(1, "Laptop", 2500);
        Inventario inventarioLaptop = new Inventario(laptop, 5);

        Cliente cliente = new Cliente(101, "Daniers", "daniel@mail.com");

        Pedido pedido = cliente.crearPedido();

        pedido.agregarProducto(laptop, 2, inventarioLaptop);

        pedido.pagar(MetodoPago.TARJETA);

        pedido.mostrarResumen();

        inventarioLaptop.mostrarStock(); 
    }
}
