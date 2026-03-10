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
 * POLIMORFISMO: maneja una lista de Transaccion que puede contener Pago o Devolucion.
 *   Al invocar procesarTransaccion(), se llama al procesar() correcto según el tipo real.
 *
 * ABSTRACCIÓN: este servicio no necesita conocer si es Pago o Devolucion para procesar;
 *   solo trabaja contra el contrato de Transaccion.
 *
 * FLUJO API:
 *   1. POST /transacciones/pagos       → crearPago()      (estado: PENDIENTE)
 *   2. PUT  /transacciones/{id}/procesar → procesarTransaccion() (estado: PROCESADO)
 *
 *   1. POST /transacciones/devoluciones → crearDevolucion() (estado: PENDIENTE)
 *   2. PUT  /transacciones/{id}/procesar → procesarTransaccion() (estado: REEMBOLSADO)
 */
@Service
public class TransaccionService {

    private final List<Transaccion> transacciones = new ArrayList<>();
    private int contadorId = 1;

    @Autowired
    private PedidoService pedidoService;

    /**
     * Crea un pago asociado a un pedido existente.
     * Solo crea la transacción; aún no la procesa.
     */
    public Pago crearPago(int idPedido, double monto, MetodoPago metodo) {
        // Valida que el pedido exista antes de crear la transacción
        pedidoService.buscarPorId(idPedido);

        Pago pago = new Pago(contadorId++, monto, metodo, idPedido);
        transacciones.add(pago);
        return pago;
    }

    /**
     * Crea una devolución asociada a un pedido existente.
     * Solo crea la transacción; aún no la procesa.
     */
    public Devolucion crearDevolucion(int idPedido, double monto, String motivo) {
        // Valida que el pedido exista antes de crear la transacción
        pedidoService.buscarPorId(idPedido);

        Devolucion devolucion = new Devolucion(contadorId++, monto, idPedido, motivo);
        transacciones.add(devolucion);
        return devolucion;
    }

    /**
     * Procesa una transacción por su id, sin importar si es Pago o Devolucion.
     * POLIMORFISMO: se invoca t.procesar() y cada subclase ejecuta su lógica.
     * Si es un Pago, adicionalmente marca el pedido como PAGADO.
     */
    public Transaccion procesarTransaccion(int idTransaccion) {
        Transaccion transaccion = buscarPorId(idTransaccion);
        transaccion.procesar(); // polimorfismo en acción

        // Si la transacción es un Pago procesado, marca el pedido como pagado
        if (transaccion instanceof Pago) {
            pedidoService.buscarPorId(transaccion.getIdReferencia())
                         .marcarComoPagado();
        }
        return transaccion;
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