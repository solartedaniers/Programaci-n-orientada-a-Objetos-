// PedidoController.java
package co.ucc.apipedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.apipedidos.dto.AgregarProductoDTO;
import co.ucc.apipedidos.dto.PedidoDTO;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.crearPedido(dto.getIdCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PostMapping("/{idPedido}/productos")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable int idPedido,
            @RequestBody AgregarProductoDTO dto) {
        Pedido pedido = pedidoService.agregarProducto(
            idPedido, dto.getIdProducto(), dto.getCantidad());
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{idPedido}/resumen")
    public ResponseEntity<Pedido> mostrarResumen(@PathVariable int idPedido) {
        return ResponseEntity.ok(pedidoService.mostrarResumen(idPedido));
    }

    @GetMapping("/{idPedido}/total")
    public ResponseEntity<Double> calcularTotal(@PathVariable int idPedido) {
        return ResponseEntity.ok(pedidoService.mostrarResumen(idPedido).getTotal());
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}