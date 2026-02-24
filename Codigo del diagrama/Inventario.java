public class Inventario {

    private Producto producto;
    private int stock;

    public Inventario(Producto producto, int stock) {
        this.producto = producto;
        this.stock = stock;
    }

    public boolean hayStock(int cantidad) {
        return stock >= cantidad;
    }

    public void descontar(int cantidad) {
        if (!hayStock(cantidad)) {
            throw new RuntimeException("Stock insuficiente de " + producto.getNombre());
        }
        stock -= cantidad;
    }

    public void agregar(int cantidad) {
        stock += cantidad;
    }

    public void mostrarStock() {
        System.out.println("Stock actual de " + producto.getNombre() + ": " + stock);
    }
}
