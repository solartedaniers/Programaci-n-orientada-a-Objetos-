package co.ucc.apipedidos.repository;

import co.ucc.apipedidos.models.Inventario;
import co.ucc.apipedidos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    Inventario findByProducto(Producto producto);
}