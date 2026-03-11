package co.ucc.apipedidos.dto;
import co.ucc.apipedidos.models.enums.TipoEnvio;
public class EnvioDTO {
    private int idPedido;
    private double peso;
    private double volumen;
    private TipoEnvio tipoEnvio;
    public EnvioDTO() {}
    public int getIdPedido()        { return idPedido; }
    public double getPeso()         { return peso; }
    public double getVolumen()      { return volumen; }
    public TipoEnvio getTipoEnvio() { return tipoEnvio; }
    public void setIdPedido(int v)         { this.idPedido = v; }
    public void setPeso(double v)          { this.peso = v; }
    public void setVolumen(double v)       { this.volumen = v; }
    public void setTipoEnvio(TipoEnvio v)  { this.tipoEnvio = v; }
}