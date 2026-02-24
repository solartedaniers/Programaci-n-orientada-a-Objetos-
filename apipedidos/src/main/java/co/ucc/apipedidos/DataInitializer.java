package co.ucc.apipedidos;

import co.ucc.apipedidos.models.*;
import co.ucc.apipedidos.models.enums.*;
import co.ucc.apipedidos.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final InventarioRepository inventarioRepository;
    private final PedidoRepository pedidoRepository;

    public DataInitializer(ClienteRepository clienteRepository,
                           ProductoRepository productoRepository,
                           InventarioRepository inventarioRepository,
                           PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.inventarioRepository = inventarioRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (clienteRepository.count() > 0) return;

        // ===== CLIENTES =====
        Cliente c1 = new Cliente();
        c1.setNombreCliente("Alexander García");
        c1.setCorreo("alexander@ucc.edu.co");
        clienteRepository.save(c1);

        Cliente c2 = new Cliente();
        c2.setNombreCliente("María López");
        c2.setCorreo("maria@ucc.edu.co");
        clienteRepository.save(c2);

        Cliente c3 = new Cliente();
        c3.setNombreCliente("Juan Pérez");
        c3.setCorreo("juan@ucc.edu.co");
        clienteRepository.save(c3);

        // ===== PRODUCTOS =====
        Producto p1 = new Producto();
        p1.setNombre("Laptop Lenovo");
        p1.setPrecio(2500000);
        productoRepository.save(p1);

        Producto p2 = new Producto();
        p2.setNombre("Mouse Inalámbrico");
        p2.setPrecio(45000);
        productoRepository.save(p2);

        Producto p3 = new Producto();
        p3.setNombre("Teclado Mecánico");
        p3.setPrecio(180000);
        productoRepository.save(p3);

        Producto p4 = new Producto();
        p4.setNombre("Monitor 24 pulgadas");
        p4.setPrecio(750000);
        productoRepository.save(p4);

        // ===== INVENTARIO =====
        Inventario i1 = new Inventario(p1, 10);
        inventarioRepository.save(i1);

        Inventario i2 = new Inventario(p2, 50);
        inventarioRepository.save(i2);

        Inventario i3 = new Inventario(p3, 25);
        inventarioRepository.save(i3);

        Inventario i4 = new Inventario(p4, 15);
        inventarioRepository.save(i4);

        // ===== PEDIDO 1 — PAGADO con TARJETA =====
        Pago pago1 = new Pago(MetodoPago.TARJETA);
        pago1.setEstado(EstadoPago.APROBADO);

        DetallePedido d1 = new DetallePedido(p1, 1);
        DetallePedido d2 = new DetallePedido(p2, 2);

        Pedido pedido1 = new Pedido(c1);
        pedido1.getDetalles().add(d1);
        pedido1.getDetalles().add(d2);
        pedido1.setTotal(d1.getSuptotal() + d2.getSuptotal());
        pedido1.setEstado(EstadoPedido.PAGADO);
        pedido1.setPago(pago1);
        pedidoRepository.save(pedido1);

        // ===== PEDIDO 2 — CREADO con PSE =====
        Pago pago2 = new Pago(MetodoPago.PSE);
        pago2.setEstado(EstadoPago.PENDIENTE);

        DetallePedido d3 = new DetallePedido(p3, 1);
        DetallePedido d4 = new DetallePedido(p4, 1);

        Pedido pedido2 = new Pedido(c2);
        pedido2.getDetalles().add(d3);
        pedido2.getDetalles().add(d4);
        pedido2.setTotal(d3.getSuptotal() + d4.getSuptotal());
        pedido2.setEstado(EstadoPedido.CREADO);
        pedido2.setPago(pago2);
        pedidoRepository.save(pedido2);

        // ===== PEDIDO 3 — ENVIADO con EFECTIVO =====
        Pago pago3 = new Pago(MetodoPago.EFECTIVO);
        pago3.setEstado(EstadoPago.APROBADO);

        DetallePedido d5 = new DetallePedido(p2, 3);

        Pedido pedido3 = new Pedido(c3);
        pedido3.getDetalles().add(d5);
        pedido3.setTotal(d5.getSuptotal());
        pedido3.setEstado(EstadoPedido.ENVIADO);
        pedido3.setPago(pago3);
        pedidoRepository.save(pedido3);

        System.out.println("✅ Datos de prueba cargados correctamente");
    }
}