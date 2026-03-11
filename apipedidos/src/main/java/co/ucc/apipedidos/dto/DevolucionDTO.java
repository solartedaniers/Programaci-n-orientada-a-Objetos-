package co.ucc.apipedidos.dto;
public class DevolucionDTO {
    private int idPedido;
    private double monto;
    private String motivo;
    public DevolucionDTO() {}
    public int getIdPedido()  { return idPedido; }
    public double getMonto()  { return monto; }
    public String getMotivo() { return motivo; }
    public void setIdPedido(int v)   { this.idPedido = v; }
    public void setMonto(double v)   { this.monto = v; }
    public void setMotivo(String v)  { this.motivo = v; }
}