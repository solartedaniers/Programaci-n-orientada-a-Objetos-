package co.ucc.apipedidos.services;

import co.ucc.apipedidos.models.Inventario;
import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public boolean hayStock(Producto producto, int cantidad) {
        Inventario inventario = inventarioRepository.findByProducto(producto);
        return inventario != null && inventario.getStock() >= cantidad;
    }

    public void descontar(Producto producto, int cantidad) {
        Inventario inventario = inventarioRepository.findByProducto(producto);
        inventario.setStock(inventario.getStock() - cantidad);
        inventarioRepository.save(inventario);
    }

    public void agregar(Producto producto, int cantidad) {
        Inventario inventario = inventarioRepository.findByProducto(producto);
        inventario.setStock(inventario.getStock() + cantidad);
        inventarioRepository.save(inventario);
    }

    public Inventario mostrarStock(Producto producto) {
        return inventarioRepository.findByProducto(producto);
    }
}