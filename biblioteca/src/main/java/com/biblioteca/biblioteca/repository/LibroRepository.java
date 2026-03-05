package com.biblioteca.biblioteca.repository;


import com.biblioteca.biblioteca.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByActivoTrue();
    Optional<Libro> findByIsbn(String isbn);
}