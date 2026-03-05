package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Pago;
import co.ucc.apipedidos.models.enums.MetodoPago;

@Service
public class PagoService {

    private final List<Pago> pagos = new ArrayList<>();
    private int contadorId = 1;

    public Pago registrarPago(int idPedido, double monto, MetodoPago metodo) {
        Pago nuevoPago = new Pago(contadorId++, monto, metodo, idPedido);
        pagos.add(nuevoPago);
        return nuevoPago;
    }

    public List<Pago> listarPagos() {
        return Collections.unmodifiableList(pagos);
    }

    public Pago procesarPago(int idPago) {
        Pago pago = buscarPorId(idPago);
        pago.procesarPago();
        return pago;
    }

    Pago buscarPorId(int idPago) {
        return pagos.stream()
                .filter(p -> p.getIdPago() == idPago)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Pago no encontrado con id: " + idPago));
    }
}