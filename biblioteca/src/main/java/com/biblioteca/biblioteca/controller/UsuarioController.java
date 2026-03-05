package com.biblioteca.biblioteca.controller;


import com.biblioteca.biblioteca.models.Usuario;
import com.biblioteca.biblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.registrarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> catalogo() {
        return ResponseEntity.ok(usuarioService.consultarCatalogo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.consultarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Long id,
                                           @RequestBody Usuario datos) {
        return ResponseEntity.ok(usuarioService.editarUsuario(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBaja(@PathVariable Long id) {
        usuarioService.darDeBajaUsuario(id);
        return ResponseEntity.noContent().build();
    }
}