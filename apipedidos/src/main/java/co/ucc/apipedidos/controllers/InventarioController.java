package co.ucc.apipedidos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.apipedidos.services.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/{idProducto}/stock")
    public ResponseEntity<Integer> consultarStock(@PathVariable int idProducto) {
        return ResponseEntity.ok(inventarioService.mostrarStock(idProducto));
    }

    @GetMapping("/{idProducto}/haystock")
    public ResponseEntity<Boolean> verificarStock(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        return ResponseEntity.ok(inventarioService.hayStock(idProducto, cantidad));
    }

    @PutMapping("/{idProducto}/descontar")
    public ResponseEntity<String> descontarStock(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        inventarioService.descontar(idProducto, cantidad);
        return ResponseEntity.ok("Stock descontado correctamente");
    }

    @PutMapping("/{idProducto}/agregar")
    public ResponseEntity<String> agregarStock(
            @PathVariable int idProducto,
            @RequestParam int cantidad) {
        inventarioService.agregar(idProducto, cantidad);
        return ResponseEntity.ok("Stock agregado correctamente");
    }
}