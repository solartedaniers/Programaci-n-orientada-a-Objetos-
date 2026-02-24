package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.EstadoPago;
import co.ucc.apipedidos.models.enums.MetodoPago;
import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodo;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    public Pago() {}

    public Pago(MetodoPago metodo) {
        this.metodo = metodo;
        this.estado = EstadoPago.PENDIENTE;
    }

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public MetodoPago getMetodo() { return metodo; }
    public void setMetodo(MetodoPago metodo) { this.metodo = metodo; }

    public EstadoPago getEstado() { return estado; }
    public void setEstado(EstadoPago estado) { this.estado = estado; }
}