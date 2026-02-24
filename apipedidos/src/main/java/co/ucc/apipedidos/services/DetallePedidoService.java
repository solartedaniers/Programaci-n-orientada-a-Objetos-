package co.ucc.apipedidos.services;

import co.ucc.apipedidos.models.DetallePedido;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {

    public void calcularSubTotal(DetallePedido detalle) {
        double subtotal = detalle.getProducto().getPrecio() * detalle.getCantidad();
        detalle.setSubtotal(subtotal);
    }
}