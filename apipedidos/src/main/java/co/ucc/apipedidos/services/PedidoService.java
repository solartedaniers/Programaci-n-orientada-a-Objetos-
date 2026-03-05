package co.ucc.apipedidos.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.DetallePedido;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.Producto;

@Service
public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();

    private final List<Producto> catalogoProductos = new ArrayList<>(List.of(
            new Producto(1, "Laptop",     2500000.0),
            new Producto(2, "Mouse",        45000.0),
            new Producto(3, "Teclado",      85000.0),
            new Producto(4, "Monitor",     950000.0),
            new Producto(5, "Audifonos",   120000.0)
    ));

    private int contadorPedido  = 1;
    private int contadorDetalle = 1;

    public Pedido crearPedido(String nombreCliente) {
        Pedido pedido = new Pedido(contadorPedido++, nombreCliente);
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido agregarProducto(int idPedido, int idProducto, int cantidad) {
        Pedido pedido     = buscarPorId(idPedido);
        Producto producto = buscarProducto(idProducto);

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

    public void marcarComoPagado(int idPedido) {
        Pedido pedido = buscarPorId(idPedido);
        pedido.marcarComoPagado();
    }

    public List<Producto> listarProductos() {
        return Collections.unmodifiableList(catalogoProductos);
    }

    Pedido buscarPorId(int idPedido) {
        return pedidos.stream()
                .filter(p -> p.getIdPedido() == idPedido)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Pedido no encontrado con id: " + idPedido));
    }

    private Producto buscarProducto(int idProducto) {
        return catalogoProductos.stream()
                .filter(p -> p.getIdProducto() == idProducto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                    "Producto no encontrado con id: " + idProducto));
    }
}