// Transaccion.java
package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.EstadoTransaccion;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transaccion", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int idPedido;

    @Column(nullable = false)
    private double monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTransaccion estado;

    public int getId()                    { return id; }
    public int getIdPedido()              { return idPedido; }
    public double getMonto()              { return monto; }
    public EstadoTransaccion getEstado()  { return estado; }

    private void setId(int id)                   { this.id = id; }
    public void setIdPedido(int idPedido)         { this.idPedido = idPedido; }
    public void setMonto(double monto)            { this.monto = monto; }
    protected void setEstado(EstadoTransaccion e) { this.estado = e; }

    public abstract void procesar();
}