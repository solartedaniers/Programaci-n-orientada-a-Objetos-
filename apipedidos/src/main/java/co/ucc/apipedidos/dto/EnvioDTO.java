// EnvioDTO.java
package co.ucc.apipedidos.dto;
import co.ucc.apipedidos.models.enums.TipoEnvio;
public class EnvioDTO {
    private int idPedido;
    private double peso;
    private double volumen;
    private TipoEnvio tipoEnvio;
    public int getIdPedido()                { return idPedido; }
    public double getPeso()                 { return peso; }
    public double getVolumen()              { return volumen; }
    public TipoEnvio getTipoEnvio()         { return tipoEnvio; }
    public void setIdPedido(int id)         { this.idPedido = id; }
    public void setPeso(double p)           { this.peso = p; }
    public void setVolumen(double v)        { this.volumen = v; }
    public void setTipoEnvio(TipoEnvio t)   { this.tipoEnvio = t; }
}