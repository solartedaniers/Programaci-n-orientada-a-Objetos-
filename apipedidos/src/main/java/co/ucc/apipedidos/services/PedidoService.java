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

/**
 * Servicio que gestiona la creación y consulta de pedidos.
 *
 * MODULARIDAD: delega la verificación de stock a InventarioService.
 * ENCAPSULAMIENTO: las listas internas son privadas; se exponen como inmutables.
 */
@Service
public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Producto> productos;

    private int contadorPedido  = 1;
    private int contadorDetalle = 1;

    @Autowired
    private InventarioService inventarioService;

    public PedidoService() {
        // Catálogo inicial de productos
        this.productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Laptop",        2500000.0),
            new Producto(2, "Mouse",          45000.0),
            new Producto(3, "Teclado",        80000.0),
            new Producto(4, "Monitor",       650000.0),
            new Producto(5, "Auriculares",   120000.0)
        ));
    }

    public Pedido crearPedido(String nombreCliente) {
        Pedido pedido = new Pedido(contadorPedido++, nombreCliente);
        pedidos.add(pedido);
        return pedido;
    }

    /**
     * Agrega un producto al pedido descontando stock del inventario.
     */
    public Pedido agregarProducto(int idPedido, int idProducto, int cantidad) {
        Pedido pedido    = buscarPorId(idPedido);
        Producto producto = buscarProducto(idProducto);

        inventarioService.descontar(idProducto, cantidad);

        DetallePedido detalle = new DetallePedido(
            contadorDetalle++,
            producto.getNombre(),
            producto.getPrecio(),
            cantidad
        );
        pedido.agregarDetalle(detalle);
        return pedido;
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