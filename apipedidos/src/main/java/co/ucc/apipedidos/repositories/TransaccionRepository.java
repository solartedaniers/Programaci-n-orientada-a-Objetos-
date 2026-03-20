// TransaccionRepository.java
package co.ucc.apipedidos.repositories;

import co.ucc.apipedidos.models.Transaccion;
import co.ucc.apipedidos.models.enums.EstadoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    List<Transaccion> findByIdPedido(int idPedido);
    List<Transaccion> findByEstado(EstadoTransaccion estado);
}