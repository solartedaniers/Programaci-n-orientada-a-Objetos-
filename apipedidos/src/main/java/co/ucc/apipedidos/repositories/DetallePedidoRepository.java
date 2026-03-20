// DetallePedidoRepository.java
package co.ucc.apipedidos.repositories;

import co.ucc.apipedidos.models.DetallePedido;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido(Pedido pedido);
    List<DetallePedido> findByProducto(Producto producto);
}