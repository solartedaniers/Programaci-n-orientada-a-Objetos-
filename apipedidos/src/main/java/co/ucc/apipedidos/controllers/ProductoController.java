// ProductoController.java
package co.ucc.apipedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.services.PedidoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(pedidoService.listarProductos());
    }
}