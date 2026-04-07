// Pedido.java
package co.ucc.apipedidos.models;

import java.util.ArrayList;
import java.util.List;

import co.ucc.apipedidos.models.enums.EstadoPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(nullable = false)
    private double total;

    // Se agrega 'final' porque la lista se inicializa aquí y no tiene un Setter.
    // Esto es seguro para JPA y sigue las recomendaciones de diseño.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<DetallePedido> detalles = new ArrayList<>();

    // Getters
    public int getId() { 
        return id; 
    }
    
    public Cliente getCliente() { 
        return cliente; 
    }
    
    public EstadoPedido getEstado() { 
        return estado; 
    }
    
    public double getTotal() { 
        return total; 
    }
    
    public List<DetallePedido> getDetalles() { 
        return detalles; 
    }

    // Setters
    public void setCliente(Cliente c) { 
        this.cliente = c; 
    }
    
    public void setEstado(EstadoPedido e) { 
        this.estado = e; 
    }
    
    public void setTotal(double t) { 
        this.total = t; 
    }

}