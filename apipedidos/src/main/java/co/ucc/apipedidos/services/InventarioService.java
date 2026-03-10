package co.ucc.apipedidos.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona el stock de productos en inventario.
 *
 * MODULARIDAD: responsabilidad única — solo opera sobre stock.
 * ENCAPSULAMIENTO: el mapa de stock es privado; su modificación ocurre
 * exclusivamente mediante los métodos descontar() y agregar(), que incluyen
 * validaciones de negocio.
 */
@Service
public class InventarioService {

    private final Map<Integer, Integer> stock = new HashMap<>();

    public InventarioService() {
        stock.put(1, 10);
        stock.put(2, 50);
        stock.put(3, 30);
        stock.put(4, 15);
        stock.put(5, 25);
    }

    public int mostrarStock(int idProducto) {
        return stock.getOrDefault(idProducto, 0);
    }

    public boolean hayStock(int idProducto, int cantidad) {
        return stock.getOrDefault(idProducto, 0) >= cantidad;
    }

    /**
     * Descuenta unidades del stock. Lanza excepción si no hay suficiente stock.
     */
    public void descontar(int idProducto, int cantidad) {
        int actual = stock.getOrDefault(idProducto, 0);
        if (actual < cantidad) {
            throw new IllegalArgumentException(
                "Stock insuficiente para producto " + idProducto +
                ". Disponible: " + actual + ", requerido: " + cantidad);
        }
        stock.put(idProducto, actual - cantidad);
    }

    /**
     * Agrega unidades al stock. La cantidad debe ser positiva.
     */
    public void agregar(int idProducto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad a agregar debe ser mayor que cero.");
        }
        int actual = stock.getOrDefault(idProducto, 0);
        stock.put(idProducto, actual + cantidad);
    }
}