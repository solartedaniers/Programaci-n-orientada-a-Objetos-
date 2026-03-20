public class carro extends Vehiculo {

    // Constructor
    public carro(String marca, String placa, String color) {
        super(marca, placa, color);
    }

    // Sobreescribir mostrarInfo
    @Override
    public void mostrarInfo() {
        System.out.println("[ CARRO ]");
        super.mostrarInfo();
    }
}