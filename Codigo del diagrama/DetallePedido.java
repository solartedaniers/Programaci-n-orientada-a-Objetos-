public class DetallePedido {

    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        subtotal = producto.getPrecio() * cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    // ✅ ESTE MÉTODO FALTABA
    public int getCantidad() {
        return cantidad;
    }
}
