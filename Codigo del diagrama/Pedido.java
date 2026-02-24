import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<DetallePedido> detalles;
    private EstadoPedido estado;
    private double total;
    private Pago pago;

    // ✅ Constructor correcto
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.estado = EstadoPedido.CREADO;
    }

    public void agregarProducto(Producto producto, int cantidad, Inventario inventario) {

        if (!inventario.hayStock(cantidad)) {
            throw new RuntimeException("No hay stock disponible");
        }

        inventario.descontar(cantidad);
        detalles.add(new DetallePedido(producto, cantidad));
        calcularTotal();
    }

    private void calcularTotal() {
        total = detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
    }

    public void pagar(MetodoPago metodo) {
        pago = new Pago(metodo);
        pago.procesarPago();

        if (pago.getEstado() == EstadoPago.APROBADO) {
            estado = EstadoPedido.PAGADO;
        }
    }

    public void mostrarResumen() {
        System.out.println("===== RESUMEN DEL PEDIDO =====");
        System.out.println(cliente);

        for (DetallePedido d : detalles) {
            System.out.println(d.getProducto().getNombre() +
                    " x" + d.getCantidad() +
                    " = $" + d.getSubtotal());
        }

        System.out.println("TOTAL: $" + total);
        System.out.println("ESTADO: " + estado);
    }
}
