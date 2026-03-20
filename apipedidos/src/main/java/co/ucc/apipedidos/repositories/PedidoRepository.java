// PedidoRepository.java
package co.ucc.apipedidos.repositories;

import co.ucc.apipedidos.models.Cliente;
import co.ucc.apipedidos.models.Pedido;
import co.ucc.apipedidos.models.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByClienteId(int clienteId);
    List<Pedido> findByEstado(EstadoPedido estado);
}