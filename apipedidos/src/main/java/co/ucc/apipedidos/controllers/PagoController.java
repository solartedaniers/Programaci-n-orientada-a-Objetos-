package co.ucc.apipedidos.controllers;

import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.services.PedidoService;
import co.ucc.apipedidos.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PagoService pagoService;

    @PostMapping("/{idPedido}/pagar")
    public ResponseEntity<Pedido> pagar(
            @PathVariable int idPedido,
            @RequestParam MetodoPago metodo) {
        Pedido pedido = pedidoService.pagar(idPedido, metodo);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{idPago}/procesar")
    public ResponseEntity<String> procesarPago(@PathVariable int idPago) {
        pagoService.procesarPago(idPago);
        return ResponseEntity.ok("Pago procesado correctamente");
    }
}