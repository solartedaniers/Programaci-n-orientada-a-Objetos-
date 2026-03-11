package co.ucc.apipedidos.dto;
public class ClienteDTO {
    private String nombre;
    private String correo;
    public ClienteDTO() {}
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
}