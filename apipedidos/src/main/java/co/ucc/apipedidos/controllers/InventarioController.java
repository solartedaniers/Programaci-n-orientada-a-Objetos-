package co.ucc.apipedidos.controllers;

import co.ucc.apipedidos.models.Inventario;
import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.repository.ProductoRepository;
import co.ucc.apipedidos.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/{idProducto}/stock")
    public ResponseEntity<Inventario> mostrarStock(@PathVariable int idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        return ResponseEntity.ok(inventarioService.mostrarStock(producto));
    }

    @GetMapping("/{idProducto}/haystock")
    public ResponseEntity<Boolean> hayStock(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        return ResponseEntity.ok(inventarioService.hayStock(producto, cantidad));
    }

    @PutMapping("/{idProducto}/descontar")
    public ResponseEntity<String> descontar(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        inventarioService.descontar(producto, cantidad);
        return ResponseEntity.ok("Stock descontado correctamente");
    }

    @PutMapping("/{idProducto}/agregar")
    public ResponseEntity<String> agregar(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        inventarioService.agregar(producto, cantidad);
        return ResponseEntity.ok("Stock agregado correctamente");
    }
}