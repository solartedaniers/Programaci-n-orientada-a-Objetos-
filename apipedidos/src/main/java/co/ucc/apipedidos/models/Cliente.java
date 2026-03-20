// Cliente.java
package co.ucc.apipedidos.models;

import jakarta.persistence.*;

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

    private void setId(int id)         { this.id = id; }
    public void setNombre(String n)    { this.nombre = n; }
    public void setCorreo(String c)    { this.correo = c; }
}