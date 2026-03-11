package co.ucc.apipedidos.dto;
import co.ucc.apipedidos.models.enums.MetodoPago;
public class PagoDTO {
    private int idPedido;
    private double monto;
    private MetodoPago metodo;
    public PagoDTO() {}
    public int getIdPedido()      { return idPedido; }
    public double getMonto()      { return monto; }
    public MetodoPago getMetodo() { return metodo; }
    public void setIdPedido(int v)       { this.idPedido = v; }
    public void setMonto(double v)       { this.monto = v; }
    public void setMetodo(MetodoPago v)  { this.metodo = v; }
}