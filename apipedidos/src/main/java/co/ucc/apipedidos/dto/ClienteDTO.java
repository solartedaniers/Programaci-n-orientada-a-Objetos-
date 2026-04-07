package co.ucc.apipedidos.dto;

public class ClienteDTO {
    private String nombre;
    private String correo;
    private String genero;
    private String numeroIdentificacion;
    private String tipoIdentificacion;

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getGenero() { return genero; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }

    public void setNombre(String n) { this.nombre = n; }
    public void setCorreo(String c) { this.correo = c; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
}
