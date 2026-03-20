import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    static List<Vehiculo> vehiculos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE REGISTRO DE VEHÍCULOS");
        System.out.println("==========================================");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar Carro");
            System.out.println("2. Registrar Moto");
            System.out.println("3. Listar todos los vehículos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine().trim());

            switch (opcion) {
                case 1:
                    registrarCarro();
                    break;
                case 2:
                    registrarMoto();
                    break;
                case 3:
                    listarVehiculos();
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    static void registrarCarro() {
        System.out.println("\n-- Registro de Carro --");
        System.out.print("Marca : ");
        String marca = sc.nextLine();
        System.out.print("Placa : ");
        String placa = sc.nextLine();
        System.out.print("Color : ");
        String color = sc.nextLine();

        carro carro = new carro(marca, placa, color);
        vehiculos.add(carro);
        System.out.println("✔ Carro registrado exitosamente.");
    }

    static void registrarMoto() {
        System.out.println("\n-- Registro de Moto --");
        System.out.print("Marca : ");
        String marca = sc.nextLine();
        System.out.print("Placa : ");
        String placa = sc.nextLine();
        System.out.print("Color : ");
        String color = sc.nextLine();

        moto moto = new moto(marca, placa, color);
        vehiculos.add(moto);
        System.out.println("✔ Moto registrada exitosamente.");
    }

    static void listarVehiculos() {
        System.out.println("\n-- Lista de Vehículos Registrados --");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados aún.");
            return;
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println("\nVehículo #" + (i + 1));
            vehiculos.get(i).mostrarInfo();
        }
    }
}