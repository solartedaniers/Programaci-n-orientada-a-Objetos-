package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.models.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByCedula(usuario.getCedula()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con esa cédula.");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> consultarCatalogo() {
        return usuarioRepository.findByActivoTrue();
    }

    public Usuario consultarPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("El ID del usuario no puede ser nulo.");
            
        }
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }

    public Usuario editarUsuario(Long id, Usuario datos) {
        Usuario usuario = consultarPorId(id);
        if (!usuario.isActivo()) {
            throw new RuntimeException("No se puede editar un usuario dado de baja.");
        }
        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setEmail(datos.getEmail());
        usuario.setTelefono(datos.getTelefono());
        return usuarioRepository.save(usuario);
    }

    public void darDeBajaUsuario(Long id) {
        Usuario usuario = consultarPorId(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}