package co.ucc.apipedidos.models;

public class Producto {

    private final int idProducto;
    private final String nombre;
    private final double precio;

    public Producto(int idProducto, String nombre, double precio) {
        this.idProducto = idProducto;
        this.nombre     = nombre;
        this.precio     = precio;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre()  { return nombre; }
    public double getPrecio()  { return precio; }
}