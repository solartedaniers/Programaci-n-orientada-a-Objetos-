// PagoDTO.java
package co.ucc.apipedidos.dto;
import co.ucc.apipedidos.models.enums.MetodoPago;
public class PagoDTO {
    private int idPedido;
    private double monto;
    private MetodoPago metodo;
    public int getIdPedido()                { return idPedido; }
    public double getMonto()                { return monto; }
    public MetodoPago getMetodo()           { return metodo; }
    public void setIdPedido(int id)         { this.idPedido = id; }
    public void setMonto(double m)          { this.monto = m; }
    public void setMetodo(MetodoPago m)     { this.metodo = m; }
}