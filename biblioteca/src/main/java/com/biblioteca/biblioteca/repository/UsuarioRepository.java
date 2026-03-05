package com.biblioteca.biblioteca.repository;


import com.biblioteca.biblioteca.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByActivoTrue();
    Optional<Usuario> findByCedula(String cedula);
}