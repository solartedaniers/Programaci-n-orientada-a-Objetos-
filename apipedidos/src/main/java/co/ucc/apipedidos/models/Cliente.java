package co.ucc.apipedidos.models;

/**
 * Representa un cliente del sistema.
 *
 * ENCAPSULAMIENTO: todos los atributos son privados y final (inmutables).
 * No se exponen setters ya que un cliente no modifica su identidad una vez creado.
 */
public class Cliente {

    private final int idCliente;
    private final String nombre;
    private final String correo;

    public Cliente(int idCliente, String nombre, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo del cliente no puede estar vacío.");
        }
        this.idCliente = idCliente;
        this.nombre    = nombre;
        this.correo    = correo;
    }

    public int getIdCliente()  { return idCliente; }
    public String getNombre()  { return nombre; }
    public String getCorreo()  { return correo; }
}