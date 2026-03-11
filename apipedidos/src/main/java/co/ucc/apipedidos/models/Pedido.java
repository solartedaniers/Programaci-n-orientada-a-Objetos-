package co.ucc.apipedidos.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.ucc.apipedidos.models.enums.EstadoPedido;

public class Pedido {

    private final int idPedido;
    private final String nombreCliente;
    private final List<DetallePedido> detalles;
    private EstadoPedido estado;
    private double total;

    public Pedido(int idPedido, String nombreCliente) {
        this.idPedido      = idPedido;
        this.nombreCliente = nombreCliente;
        this.estado        = EstadoPedido.CREADO;
        this.detalles      = new ArrayList<>();
        this.total         = 0.0;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.detalles.add(detalle);
    }

    public void marcarComoPagado() {
        if (this.estado == EstadoPedido.CREADO) {
            throw new IllegalStateException(
                "No se puede pagar un pedido sin productos.");
        }
        this.estado = EstadoPedido.PAGADO;
    }

    public int getIdPedido()         { return idPedido; }
    public String getNombreCliente() { return nombreCliente; }
    public EstadoPedido getEstado()  { return estado; }
    public double getTotal()         { return total; }

    public List<DetallePedido> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }
}