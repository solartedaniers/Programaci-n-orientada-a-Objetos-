package co.ucc.apipedidos.dto;
public class AgregarProductoDTO {
    private int idProducto;
    private int cantidad;
    public AgregarProductoDTO() {}
    public int getIdProducto() { return idProducto; }
    public int getCantidad()   { return cantidad; }
    public void setIdProducto(int v) { this.idProducto = v; }
    public void setCantidad(int v)   { this.cantidad = v; }
}