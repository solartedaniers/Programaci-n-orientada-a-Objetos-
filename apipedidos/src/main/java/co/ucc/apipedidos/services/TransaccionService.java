// TransaccionService.java
package co.ucc.apipedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Devolucion;
import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.Transaccion;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.repositories.PedidoRepository;
import co.ucc.apipedidos.repositories.TransaccionRepository;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pago realizarPago(int idPedido, double monto, MetodoPago metodo) {
        validarPedido(idPedido);
        if (monto <= 0)
            throw new IllegalArgumentException("El monto debe ser positivo");
        Pago pago = new Pago();
        pago.setIdPedido(idPedido);
        pago.setMonto(monto);
        pago.setMetodoPago(metodo);
        pago.procesar();
        return (Pago) transaccionRepository.save(pago);
    }

    public Devolucion realizarDevolucion(int idPedido, double monto, String motivo) {
        validarPedido(idPedido);
        if (motivo == null || motivo.isBlank())
            throw new IllegalArgumentException("El motivo es obligatorio");
        Devolucion dev = new Devolucion();
        dev.setIdPedido(idPedido);
        dev.setMonto(monto);
        dev.setMotivo(motivo);
        dev.procesar();
        return (Devolucion) transaccionRepository.save(dev);
    }

    public Transaccion procesarTransaccion(int id) {
        Transaccion t = buscarTransaccion(id);
        t.procesar();
        return transaccionRepository.save(t);
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion buscarTransaccion(int id) {
        return transaccionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaccion no encontrada: " + id));
    }

    private void validarPedido(int idPedido) {
        pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + idPedido));
    }
}