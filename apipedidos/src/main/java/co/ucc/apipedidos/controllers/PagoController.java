package co.ucc.apipedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.services.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> registrar(
            @RequestParam int idPedido,
            @RequestParam double monto,
            @RequestParam MetodoPago metodo) {
        Pago pago = pagoService.registrarPago(idPedido, monto, metodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.listarPagos());
    }

    @PutMapping("/{idPago}/procesar")
    public ResponseEntity<Pago> procesar(@PathVariable int idPago) {
        Pago pago = pagoService.procesarPago(idPago);
        return ResponseEntity.ok(pago);
    }
}