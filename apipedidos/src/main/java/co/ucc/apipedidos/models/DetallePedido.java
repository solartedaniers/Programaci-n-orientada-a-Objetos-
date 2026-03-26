// DetallePedido.java
package co.ucc.apipedidos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public int getId()                 { return id; }
    public int getCantidad()           { return cantidad; }
    public double getPrecioUnitario()  { return precioUnitario; }
    public Producto getProducto()      { return producto; }
    public Pedido getPedido()          { return pedido; }
    public double getSubtotal()        { return precioUnitario * cantidad; }

    public void setCantidad(int c)             { this.cantidad = c; }
    public void setPrecioUnitario(double p)    { this.precioUnitario = p; }
    public void setProducto(Producto p)        { this.producto = p; }
    public void setPedido(Pedido p)            { this.pedido = p; }
}
