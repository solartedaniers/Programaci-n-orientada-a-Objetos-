package co.ucc.apipedidos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventario;

    @OneToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private int stock;

    public Inventario() {}

    public Inventario(Producto producto, int stock) {
        this.producto = producto;
        this.stock = stock;
    }

    public int getIdInventario() { return idInventario; }
    public void setIdInventario(int idInventario) { this.idInventario = idInventario; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}