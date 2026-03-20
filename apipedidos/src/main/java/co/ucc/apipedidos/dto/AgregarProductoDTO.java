// AgregarProductoDTO.java
package co.ucc.apipedidos.dto;
public class AgregarProductoDTO {
    private int idProducto;
    private int cantidad;
    public int getIdProducto()          { return idProducto; }
    public int getCantidad()            { return cantidad; }
    public void setIdProducto(int id)   { this.idProducto = id; }
    public void setCantidad(int c)      { this.cantidad = c; }
}