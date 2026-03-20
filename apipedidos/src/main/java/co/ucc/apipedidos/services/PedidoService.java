// PedidoService.java
package co.ucc.apipedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ucc.apipedidos.models.Cliente;
import co.ucc.apipedidos.models.DetallePedido;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.Producto;
import co.ucc.apipedidos.models.enums.EstadoPedido;
import co.ucc.apipedidos.repositories.ClienteRepository;
import co.ucc.apipedidos.repositories.PedidoRepository;
import co.ucc.apipedidos.repositories.ProductoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private InventarioService inventarioService;

    public Pedido crearPedido(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + idCliente));
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(EstadoPedido.CREADO);
        pedido.setTotal(0.0);
        return pedidoRepository.save(pedido);
    }

    public Pedido agregarProducto(int idPedido, int idProducto, int cantidad) {
        Pedido pedido     = buscarPedido(idPedido);
        Producto producto = inventarioService.buscarProducto(idProducto);

        if (!inventarioService.hayStock(idProducto, cantidad))
            throw new IllegalStateException("Stock insuficiente para: " + producto.getNombre());

        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(producto.getPrecio());
        detalle.setProducto(producto);
        detalle.setPedido(pedido);
        pedido.getDetalles().add(detalle);

        double nuevoTotal = pedido.getDetalles()
            .stream()
            .mapToDouble(DetallePedido::getSubtotal)
            .sum();
        pedido.setTotal(nuevoTotal);

        inventarioService.descontar(idProducto, cantidad);
        return pedidoRepository.save(pedido);
    }

    public Pedido mostrarResumen(int idPedido) {
        return buscarPedido(idPedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Pedido buscarPedido(int id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }
}