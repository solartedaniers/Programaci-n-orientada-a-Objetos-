package co.ucc.apipedidos.models;

/**
 * Modelo de dominio: línea de detalle de un pedido.
 * ENCAPSULAMIENTO: subtotal calculado en constructor, sin setters (inmutable).
 */
public class DetallePedido {

    private final int idDetalle;
    private final int cantidad;
    private final double subtotal;
    private final String nombreProducto;
    private final double precioUnitario;

    public DetallePedido(int idDetalle, String nombreProducto,
                         double precioUnitario, int cantidad) {
        this.idDetalle      = idDetalle;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad       = cantidad;
        this.subtotal       = precioUnitario * cantidad;
    }

    public int getIdDetalle()          { return idDetalle; }
    public int getCantidad()           { return cantidad; }
    public double getSubtotal()        { return subtotal; }
    public String getNombreProducto()  { return nombreProducto; }
    public double getPrecioUnitario()  { return precioUnitario; }
}