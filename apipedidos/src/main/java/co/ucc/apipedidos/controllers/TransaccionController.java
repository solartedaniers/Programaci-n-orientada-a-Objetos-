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

import co.ucc.apipedidos.models.Devolucion;
import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.Transaccion;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.services.TransaccionService;

/**
 * Controlador REST para el módulo de transacciones financieras.
 *
 * FLUJO DE DOS PASOS para pago:
 *   1. POST /transacciones/pagos        → crea el pago (PENDIENTE)
 *   2. PUT  /transacciones/{id}/procesar → procesa la transacción (PROCESADO)
 *
 * FLUJO DE DOS PASOS para devolución:
 *   1. POST /transacciones/devoluciones  → crea la devolución (PENDIENTE)
 *   2. PUT  /transacciones/{id}/procesar → procesa la transacción (REEMBOLSADO)
 *
 * MODULARIDAD: este controlador delega 100% la lógica a TransaccionService.
 */
@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    /**
     * PASO 1 (Pago): Crea un pago pendiente para un pedido.
     * El pago aún no está procesado; debe llamarse a /procesar a continuación.
     */
    @PostMapping("/pagos")
    public ResponseEntity<Pago> crearPago(
            @RequestParam int idPedido,
            @RequestParam double monto,
            @RequestParam MetodoPago metodo) {
        Pago pago = transaccionService.crearPago(idPedido, monto, metodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }

    /**
     * PASO 1 (Devolución): Crea una devolución pendiente para un pedido.
     */
    @PostMapping("/devoluciones")
    public ResponseEntity<Devolucion> crearDevolucion(
            @RequestParam int idPedido,
            @RequestParam double monto,
            @RequestParam String motivo) {
        Devolucion devolucion = transaccionService.crearDevolucion(idPedido, monto, motivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(devolucion);
    }

    /**
     * PASO 2: Procesa una transacción (Pago o Devolución) por su id.
     * POLIMORFISMO: el tipo concreto determina el comportamiento de procesar().
     */
    @PutMapping("/{idTransaccion}/procesar")
    public ResponseEntity<Transaccion> procesar(@PathVariable int idTransaccion) {
        Transaccion transaccion = transaccionService.procesarTransaccion(idTransaccion);
        return ResponseEntity.ok(transaccion);
    }

    /**
     * Lista todas las transacciones (pagos y devoluciones mezclados).
     * POLIMORFISMO: la lista devuelve objetos de tipo Transaccion abstracta.
     */
    @GetMapping
    public ResponseEntity<List<Transaccion>> listar() {
        return ResponseEntity.ok(transaccionService.listarTransacciones());
    }
}