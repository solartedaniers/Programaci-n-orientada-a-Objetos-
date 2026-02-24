package co.ucc.apipedidos.models;

import co.ucc.apipedidos.models.enums.EstadoPedido;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<DetallePedido> detalles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    private double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pago")
    private Pago pago;

    public Pedido() {
        this.estado = EstadoPedido.CREADO;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.estado = EstadoPedido.CREADO;
        this.detalles = new ArrayList<>();
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Pago getPago() { return pago; }
    public void setPago(Pago pago) { this.pago = pago; }
}