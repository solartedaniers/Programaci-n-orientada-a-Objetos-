// Cliente.java
package co.ucc.apipedidos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    public int getId()        { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    public void setNombre(String n)    { this.nombre = n; }
    public void setCorreo(String c)    { this.correo = c; }
}
