package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.DetallePedido;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.models.enums.EstadoPedido;

/**
 * Servicio que concentra TODA la lógica de negocio de pedidos.
 *
 * MODULARIDAD: responsabilidad única.
 * - Validaciones, cálculo de total, orquestación: aquí.
 * - Descuento de stock: delegado a InventarioService.
 * - Mutación del modelo: a través de setters de Pedido.
 */
@Service
public class PedidoService {

    private final List<Pedido>   pedidos;
    private final List<Producto> productos;

    private int contadorPedido  = 1;
    private int contadorDetalle = 1;

    @Autowired
    private InventarioService inventarioService;

    public PedidoService() {
        this.pedidos   = new ArrayList<>();
        this.productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Laptop",      2500000.0),
            new Producto(2, "Mouse",         45000.0),
            new Producto(3, "Teclado",       80000.0),
            new Producto(4, "Monitor",      650000.0),
            new Producto(5, "Auriculares",  120000.0)
        ));
    }

    public Pedido crearPedido(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre del cliente no puede estar vacío.");
        }
        Pedido pedido = new Pedido(contadorPedido++, nombreCliente);
        pedidos.add(pedido);
        return pedido;
    }

    /**
     * Agrega un producto al pedido.
     * Lógica de negocio completa aquí, no en el modelo.
     */
    public Pedido agregarProducto(int idPedido, int idProducto, int cantidad) {
        Pedido   pedido   = buscarPorId(idPedido);
        Producto producto = buscarProducto(idProducto);

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor que cero.");
        }

        inventarioService.descontar(idProducto, cantidad);

        DetallePedido detalle = new DetallePedido(
            contadorDetalle++,
            producto.getNombre(),
            producto.getPrecio(),
            cantidad
        );

        pedido.agregarDetalle(detalle);
        pedido.setTotal(calcularTotal(pedido));
        pedido.setEstado(EstadoPedido.CON_PRODUCTOS);

        return pedido;
    }

    private double calcularTotal(Pedido pedido) {
        return pedido.getDetalles().stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    public void marcarComoPagado(int idPedido) {
        buscarPorId(idPedido).marcarComoPagado();
    }

    public Pedido mostrarResumen(int idPedido) {
        return buscarPorId(idPedido);
    }

    public List<Pedido> listarPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public List<Producto> listarProductos() {
        return Collections.unmodifiableList(productos);
    }

    public Pedido buscarPorId(int idPedido) {
        return pedidos.stream()
                .filter(p -> p.getIdPedido() == idPedido)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Pedido no encontrado con id: " + idPedido));
    }

    public Producto buscarProducto(int idProducto) {
        return productos.stream()
                .filter(p -> p.getIdProducto() == idProducto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Producto no encontrado con id: " + idProducto));
    }
}