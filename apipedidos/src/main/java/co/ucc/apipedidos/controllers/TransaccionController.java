package co.ucc.apipedidos.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.ucc.apipedidos.dto.DevolucionDTO;
import co.ucc.apipedidos.dto.PagoDTO;
import co.ucc.apipedidos.models.Devolucion;
import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.Transaccion;
import co.ucc.apipedidos.services.TransaccionService;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    /**
     * Realiza un pago completo (crea + procesa en un paso).
     * POLIMORFISMO: internamente usa Transaccion abstracta.
     */
    @PostMapping("/pagos")
    public ResponseEntity<Pago> realizarPago(@RequestBody PagoDTO dto) {
        Pago pago = transaccionService.realizarPago(
            dto.getIdPedido(), dto.getMonto(), dto.getMetodo());
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }

    /**
     * Realiza una devolución completa (crea + procesa en un paso).
     */
    @PostMapping("/devoluciones")
    public ResponseEntity<Devolucion> realizarDevolucion(@RequestBody DevolucionDTO dto) {
        Devolucion dev = transaccionService.realizarDevolucion(
            dto.getIdPedido(), dto.getMonto(), dto.getMotivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(dev);
    }

    /**
     * Procesa una transacción pendiente por su id (flujo de dos pasos).
     * POLIMORFISMO: Pago→PROCESADO, Devolucion→REEMBOLSADO.
     */
    @PutMapping("/{idTransaccion}/procesar")
    public ResponseEntity<Transaccion> procesar(@PathVariable int idTransaccion) {
        return ResponseEntity.ok(transaccionService.procesarTransaccion(idTransaccion));
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> listar() {
        return ResponseEntity.ok(transaccionService.listarTransacciones());
    }
}