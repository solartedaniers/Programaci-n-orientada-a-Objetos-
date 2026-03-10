package co.ucc.apipedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.services.PedidoService;

/**
 * Controlador REST para la gestión de pedidos.
 * MODULARIDAD: delega toda la lógica a PedidoService.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestParam String nombreCliente) {
        Pedido pedido = pedidoService.crearPedido(nombreCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PostMapping("/{idPedido}/productos")
    public ResponseEntity<Pedido> agregar(
            @PathVariable int idPedido,
            @RequestParam int idProducto,
            @RequestParam int cantidad) {
        Pedido pedido = pedidoService.agregarProducto(idPedido, idProducto, cantidad);
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