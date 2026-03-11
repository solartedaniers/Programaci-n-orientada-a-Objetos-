package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Devolucion;
import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.Transaccion;
import co.ucc.apipedidos.models.enums.MetodoPago;

/**
 * Servicio que gestiona el ciclo de vida de las transacciones financieras.
 *
 * POLIMORFISMO: maneja List<Transaccion> que contiene Pago y Devolucion.
 *   procesarTransaccion() invoca t.procesar() sin conocer el tipo concreto.
 *
 * realizarPago() y realizarDevolucion() encapsulan el flujo completo
 * (crear + procesar en un solo paso) para el controller.
 */
@Service
public class TransaccionService {

    private final List<Transaccion> transacciones = new ArrayList<>();
    private int contadorId = 1;

    @Autowired
    private PedidoService pedidoService;

    // ─── Flujo de DOS PASOS (crear → procesar por separado) ──────────────────

    /** PASO 1: Crea un pago en estado PENDIENTE. */
    public Pago crearPago(int idPedido, double monto, MetodoPago metodo) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        if (metodo == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo.");
        }
        pedidoService.buscarPorId(idPedido); // valida que el pedido exista
        Pago pago = new Pago(contadorId++, monto, metodo, idPedido);
        transacciones.add(pago);
        return pago;
    }

    /** PASO 1: Crea una devolución en estado PENDIENTE. */
    public Devolucion crearDevolucion(int idPedido, double monto, String motivo) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo no puede estar vacío.");
        }
        pedidoService.buscarPorId(idPedido);
        Devolucion devolucion = new Devolucion(contadorId++, monto, idPedido, motivo);
        transacciones.add(devolucion);
        return devolucion;
    }

    /** PASO 2: Procesa cualquier transacción. POLIMORFISMO en acción. */
    public Transaccion procesarTransaccion(int idTransaccion) {
        Transaccion t = buscarPorId(idTransaccion);
        t.procesar(); // polimorfismo: Pago→PROCESADO, Devolucion→REEMBOLSADO

        // Si es un pago procesado, marca el pedido como pagado
        if (t instanceof Pago) {
            pedidoService.marcarComoPagado(t.getIdReferencia());
        }
        return t;
    }

    // ─── Flujo de UN PASO (crear + procesar juntos) ───────────────────────────

    /**
     * Realiza un pago completo: crea y procesa en un solo paso.
     * Expuesto en el controller como "realizarPago".
     */
    public Pago realizarPago(int idPedido, double monto, MetodoPago metodo) {
        Pago pago = crearPago(idPedido, monto, metodo);
        procesarTransaccion(pago.getIdTransaccion());
        return pago;
    }

    /**
     * Realiza una devolución completa: crea y procesa en un solo paso.
     * Expuesto en el controller como "realizarDevolucion".
     */
    public Devolucion realizarDevolucion(int idPedido, double monto, String motivo) {
        Devolucion dev = crearDevolucion(idPedido, monto, motivo);
        procesarTransaccion(dev.getIdTransaccion());
        return dev;
    }

    public List<Transaccion> listarTransacciones() {
        return Collections.unmodifiableList(transacciones);
    }

    public Transaccion buscarPorId(int idTransaccion) {
        return transacciones.stream()
                .filter(t -> t.getIdTransaccion() == idTransaccion)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Transacción no encontrada con id: " + idTransaccion));
    }
}