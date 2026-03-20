public class Vehiculo {

    // Atributos
    protected String marca;
    protected String placa;
    protected String color;

    // Constructor
    public Vehiculo(String marca, String placa, String color) {
        this.marca = marca;
        this.placa = placa;
        this.color = color;
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    // Método para mostrar información
    public void mostrarInfo() {
        System.out.println("  Marca : " + marca);
        System.out.println("  Placa : " + placa);
        System.out.println("  Color : " + color);
    }
}