package co.ucc.apipedidos.models;

/**
 * Representa un producto disponible en el catálogo.
 *
 * ENCAPSULAMIENTO: atributos privados y final. Inmutable por diseño;
 * el precio y nombre no cambian en tiempo de ejecución para esta versión del sistema.
 */
public class Producto {

    private final int idProducto;
    private final String nombre;
    private final double precio;

    public Producto(int idProducto, String nombre, double precio) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.idProducto = idProducto;
        this.nombre     = nombre;
        this.precio     = precio;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre()  { return nombre; }
    public double getPrecio()  { return precio; }
}