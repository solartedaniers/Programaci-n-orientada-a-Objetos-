package co.ucc.apipedidos.models;

/**
 * Representa una línea de detalle dentro de un pedido (producto + cantidad).
 *
 * ENCAPSULAMIENTO: todos los atributos son privados y final.
 * El subtotal se calcula internamente en el constructor; no se puede inyectar
 * desde fuera, garantizando coherencia de datos.
 */
public class DetallePedido {

    private final int idDetalle;
    private final int cantidad;
    private final double subtotal;
    private final String nombreProducto;
    private final double precioUnitario;

    public DetallePedido(int idDetalle, String nombreProducto,
                         double precioUnitario, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor que cero. Recibida: " + cantidad);
        }
        if (precioUnitario < 0) {
            throw new IllegalArgumentException(
                "El precio unitario no puede ser negativo.");
        }
        this.idDetalle      = idDetalle;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad       = cantidad;
        this.subtotal       = precioUnitario * cantidad; // calculado internamente
    }

    public int getIdDetalle()          { return idDetalle; }
    public int getCantidad()           { return cantidad; }
    public double getSubtotal()        { return subtotal; }
    public String getNombreProducto()  { return nombreProducto; }
    public double getPrecioUnitario()  { return precioUnitario; }
}