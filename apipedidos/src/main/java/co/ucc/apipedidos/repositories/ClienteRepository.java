// ClienteRepository.java
package co.ucc.apipedidos.repositories;

import co.ucc.apipedidos.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByNombre(String nombre);
    Optional<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);
}
