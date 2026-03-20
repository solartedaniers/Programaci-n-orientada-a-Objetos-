// EnvioRepository.java
package co.ucc.apipedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ucc.apipedidos.models.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    List<Envio> findByIdPedido(int idPedido);
}
