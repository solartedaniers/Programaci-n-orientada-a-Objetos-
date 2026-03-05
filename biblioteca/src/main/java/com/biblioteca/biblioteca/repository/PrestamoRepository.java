package com.biblioteca.biblioteca.repository;


import com.biblioteca.biblioteca.models.Prestamo;
import com.biblioteca.biblioteca.models.EstadoPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioId(Long usuarioId);
    List<Prestamo> findByEstado(EstadoPrestamo estado);
    boolean existsByLibroIdAndEstado(Long libroId, EstadoPrestamo estado);
}