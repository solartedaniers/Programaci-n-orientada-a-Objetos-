package co.ucc.apipedidos.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

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

    public void descontar(int idProducto, int cantidad) {
        int actual = stock.getOrDefault(idProducto, 0);
        if (actual < cantidad) {
            throw new IllegalArgumentException(
                    "Stock insuficiente. Disponible: " + actual);
        }
        stock.put(idProducto, actual - cantidad);
    }

    public void agregar(int idProducto, int cantidad) {
        int actual = stock.getOrDefault(idProducto, 0);
        stock.put(idProducto, actual + cantidad);
    }
}