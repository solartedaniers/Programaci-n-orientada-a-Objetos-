// ProductoRepository.java
package co.ucc.apipedidos.repositories;

import co.ucc.apipedidos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
}