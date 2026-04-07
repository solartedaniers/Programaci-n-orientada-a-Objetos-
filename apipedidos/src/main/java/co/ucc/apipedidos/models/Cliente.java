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

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false, unique = true)
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String tipoIdentificacion;

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getGenero() { return genero; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }

    public void setNombre(String n) { this.nombre = n; }
    public void setCorreo(String c) { this.correo = c; }
    public void setGenero(String g) { this.genero = g; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public void setTipoIdentificacion(String t) { this.tipoIdentificacion = t; }
}
