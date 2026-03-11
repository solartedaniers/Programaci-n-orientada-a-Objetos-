package co.ucc.apipedidos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.ucc.apipedidos.dto.StockDTO;
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

    @PutMapping("/{idProducto}/agregar")
    public ResponseEntity<String> agregarStock(
            @PathVariable int idProducto,
            @RequestBody StockDTO dto) {
        inventarioService.agregar(idProducto, dto.getCantidad());
        return ResponseEntity.ok("Stock agregado correctamente");
    }

    @PutMapping("/{idProducto}/descontar")
    public ResponseEntity<String> descontarStock(
            @PathVariable int idProducto,
            @RequestBody StockDTO dto) {
        inventarioService.descontar(idProducto, dto.getCantidad());
        return ResponseEntity.ok("Stock descontado correctamente");
    }
}