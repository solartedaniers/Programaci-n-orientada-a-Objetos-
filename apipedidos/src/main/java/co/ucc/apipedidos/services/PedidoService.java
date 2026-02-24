package co.ucc.apipedidos.services;

import co.ucc.apipedidos.models.*;
import co.ucc.apipedidos.models.enums.EstadoPedido;
import co.ucc.apipedidos.models.enums.MetodoPago;
import co.ucc.apipedidos.repository.ClienteRepository;
import co.ucc.apipedidos.repository.PedidoRepository;
import co.ucc.apipedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private PagoService pagoService;

    public Pedido crearPedido(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow();
        Pedido pedido = new Pedido(cliente);
        return pedidoRepository.save(pedido);
    }

    public Pedido agregarProducto(int idPedido, int idProducto, int cantidad) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        Producto producto = productoRepository.findById(idProducto).orElseThrow();

        if (!inventarioService.hayStock(producto, cantidad)) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        DetallePedido detalle = new DetallePedido(producto, cantidad);
        detallePedidoService.calcularSubTotal(detalle);
        pedido.getDetalles().add(detalle);

        inventarioService.descontar(producto, cantidad);
        calcularTotal(pedido);

        return pedidoRepository.save(pedido);
    }

    public void calcularTotal(Pedido pedido) {
        double total = pedido.getDetalles()
                .stream()
                .mapToDouble(DetallePedido::getSuptotal)
                .sum();
        pedido.setTotal(total);
    }

    public Pedido pagar(int idPedido, MetodoPago metodo) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        Pago pago = pagoService.pagar(metodo);
        pedido.setPago(pago);
        pedido.setEstado(EstadoPedido.PAGADO);
        return pedidoRepository.save(pedido);
    }

    public Pedido mostrarResumen(int idPedido) {
        return pedidoRepository.findById(idPedido).orElseThrow();
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}