// DevolucionDTO.java
package co.ucc.apipedidos.dto;
public class DevolucionDTO {
    private int idPedido;
    private double monto;
    private String motivo;
    public int getIdPedido()            { return idPedido; }
    public double getMonto()            { return monto; }
    public String getMotivo()           { return motivo; }
    public void setIdPedido(int id)     { this.idPedido = id; }
    public void setMonto(double m)      { this.monto = m; }
    public void setMotivo(String m)     { this.motivo = m; }
}