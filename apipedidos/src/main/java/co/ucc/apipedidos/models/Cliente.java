package co.ucc.apipedidos.models;

/**
 * Modelo de dominio: representa un cliente.
 * ENCAPSULAMIENTO: atributos private final, sin setters (inmutable).
 */
public class Cliente {

    private final int idCliente;
    private final String nombre;
    private final String correo;

    public Cliente(int idCliente, String nombre, String correo) {
        this.idCliente = idCliente;
        this.nombre    = nombre;
        this.correo    = correo;
    }

    public int getIdCliente()  { return idCliente; }
    public String getNombre()  { return nombre; }
    public String getCorreo()  { return correo; }
}