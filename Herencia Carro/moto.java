public class moto extends Vehiculo {

    // Constructor
    public moto(String marca, String placa, String color) {
        super(marca, placa, color);
    }

    // Sobreescribir mostrarInfo
    @Override
    public void mostrarInfo() {
        System.out.println("[ MOTO ]");
        super.mostrarInfo();
    }
}