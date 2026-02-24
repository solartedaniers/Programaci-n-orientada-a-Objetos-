public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;

    public Producto(int idProducto, String nombre, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Producto[" + idProducto + "] " + nombre + " - $" + precio;
    }
}
