package co.ucc.apipedidos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    private String nombreCliente;
    private String correo;

    public Cliente() {}

    public Cliente(int idCliente, String nombreCliente, String correo) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.correo = correo;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "Cliente{idCliente=" + idCliente + ", nombre=" + nombreCliente + ", correo=" + correo + "}";
    }
}