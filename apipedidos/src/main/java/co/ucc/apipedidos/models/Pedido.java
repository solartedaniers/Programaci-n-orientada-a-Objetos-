package co.ucc.apipedidos.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.ucc.apipedidos.models.enums.EstadoPedido;

public class Pedido {

    private final int idPedido;
    private final List<DetallePedido> detalles;
    private final String nombreCliente;
    private EstadoPedido estado;        // ← NO final, cambia con marcarComoPagado()
    private double total;               // ← NO final, cambia con calcularTotal()

    public Pedido(int idPedido, String nombreCliente) {
        this.idPedido      = idPedido;
        this.nombreCliente = nombreCliente;
        this.estado        = EstadoPedido.CREADO;
        this.detalles      = new ArrayList<>();
        this.total         = 0.0;
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.detalles.add(detalle);
        this.estado = EstadoPedido.CON_PRODUCTOS;
        calcularTotal();
    }

    public double calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        return this.total;
    }

    public void marcarComoPagado() {
        this.estado = EstadoPedido.PAGADO;
    }

    public List<DetallePedido> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public int getIdPedido()         { return idPedido; }
    public EstadoPedido getEstado()  { return estado; }
    public double getTotal()         { return total; }
    public String getNombreCliente() { return nombreCliente; }
}