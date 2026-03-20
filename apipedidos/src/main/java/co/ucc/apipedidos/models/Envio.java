// Envio.java
package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.TipoEnvio;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_envio", discriminatorType = DiscriminatorType.STRING)
public abstract class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_pedido", nullable = false)
    private int idPedido;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double volumen;

    public int getId()        { return id; }
    public int getIdPedido()  { return idPedido; }
    public double getPeso()   { return peso; }
    public double getVolumen(){ return volumen; }

    public TipoEnvio getTipoEnvio() {
        return TipoEnvio.valueOf(
            getClass().getAnnotation(DiscriminatorValue.class).value()
        );
    }

    private void setId(int id)            { this.id = id; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setPeso(double peso)      { this.peso = peso; }
    public void setVolumen(double v)      { this.volumen = v; }

    public abstract double calcularCosto();
}

