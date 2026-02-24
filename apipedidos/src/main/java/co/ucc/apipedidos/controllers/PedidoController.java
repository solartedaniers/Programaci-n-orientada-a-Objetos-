package co.ucc.apipedidos.controllers;

import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/{idPedido}/productos")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable int idPedido,
            @RequestParam int idProducto,
            @RequestParam int cantidad) {
        Pedido pedido = pedidoService.agregarProducto(idPedido, idProducto, cantidad);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{idPedido}/total")
    public ResponseEntity<Double> calcularTotal(@PathVariable int idPedido) {
        Pedido pedido = pedidoService.mostrarResumen(idPedido);
        return ResponseEntity.ok(pedido.getTotal());
    }

    @GetMapping("/{idPedido}/resumen")
    public ResponseEntity<Pedido> mostrarResumen(@PathVariable int idPedido) {
        Pedido pedido = pedidoService.mostrarResumen(idPedido);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}