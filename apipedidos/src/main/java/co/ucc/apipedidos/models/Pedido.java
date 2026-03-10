package co.ucc.apipedidos.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.ucc.apipedidos.models.enums.EstadoPedido;

/**
 * Representa un pedido realizado por un cliente.
 *
 * ENCAPSULAMIENTO:
 * - idPedido y nombreCliente son final e inmutables.
 * - detalles es privado; se expone como lista no modificable (Collections.unmodifiableList).
 * - estado y total cambian solo a través de métodos controlados (agregarDetalle, marcarComoPagado).
 * - No hay setters públicos para estado ni total: el control está dentro de la clase.
 */
public class Pedido {

    private final int idPedido;
    private final List<DetallePedido> detalles;
    private final String nombreCliente;
    private EstadoPedido estado;
    private double total;

    public Pedido(int idPedido, String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        this.idPedido      = idPedido;
        this.nombreCliente = nombreCliente;
        this.estado        = EstadoPedido.CREADO;
        this.detalles      = new ArrayList<>();
        this.total         = 0.0;
    }

    /**
     * Agrega un detalle al pedido y recalcula el total.
     * El estado pasa a CON_PRODUCTOS automáticamente.
     */
    public void agregarDetalle(DetallePedido detalle) {
        this.detalles.add(detalle);
        this.estado = EstadoPedido.CON_PRODUCTOS;
        calcularTotal();
    }

    /**
     * Recalcula y actualiza el total sumando los subtotales de cada detalle.
     * Privado en intención de negocio: solo se invoca desde agregarDetalle.
     */
    public double calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
        return this.total;
    }

    /**
     * Marca el pedido como PAGADO. Solo válido si tiene productos.
     * El estado solo avanza, nunca retrocede.
     */
    public void marcarComoPagado() {
        if (this.estado == EstadoPedido.CREADO) {
            throw new IllegalStateException(
                "No se puede pagar un pedido sin productos.");
        }
        this.estado = EstadoPedido.PAGADO;
    }

    // ─── Getters (sin setters públicos) ──────────────────────────────────────

    public List<DetallePedido> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public int getIdPedido()         { return idPedido; }
    public EstadoPedido getEstado()  { return estado; }
    public double getTotal()         { return total; }
    public String getNombreCliente() { return nombreCliente; }
}