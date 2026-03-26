// Producto.java
package co.ucc.apipedidos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    public int getId()        { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock()     { return stock; }

    public void setPrecio(double p)  { this.precio = p; }
    public void setStock(int s)      { this.stock = s; }
}
