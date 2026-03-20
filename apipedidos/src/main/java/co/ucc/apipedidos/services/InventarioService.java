// InventarioService.java
package co.ucc.apipedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.repositories.ProductoRepository;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    public boolean hayStock(int idProducto, int cantidad) {
        Producto p = buscarProducto(idProducto);
        return p.getStock() >= cantidad;
    }

    public void agregar(int idProducto, int cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        Producto p = buscarProducto(idProducto);
        p.setStock(p.getStock() + cantidad);
        productoRepository.save(p);
    }

    public void descontar(int idProducto, int cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        Producto p = buscarProducto(idProducto);
        if (p.getStock() < cantidad)
            throw new IllegalStateException("Stock insuficiente");
        p.setStock(p.getStock() - cantidad);
        productoRepository.save(p);
    }

    public int mostrarStock(int idProducto) {
        return buscarProducto(idProducto).getStock();
    }

    public Producto buscarProducto(int id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }
}