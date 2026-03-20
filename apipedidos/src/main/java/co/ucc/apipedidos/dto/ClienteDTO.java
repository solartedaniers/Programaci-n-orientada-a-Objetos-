// ClienteDTO.java
package co.ucc.apipedidos.dto;
public class ClienteDTO {
    private String nombre;
    private String correo;
    public String getNombre()           { return nombre; }
    public String getCorreo()           { return correo; }
    public void setNombre(String n)     { this.nombre = n; }
    public void setCorreo(String c)     { this.correo = c; }
}