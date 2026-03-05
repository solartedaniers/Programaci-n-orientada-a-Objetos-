package com.biblioteca.biblioteca.controller;


import com.biblioteca.biblioteca.models.Libro;
import com.biblioteca.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> registrar(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.registrarLibro(libro));
    }

    @GetMapping
    public ResponseEntity<List<Libro>> catalogo() {
        return ResponseEntity.ok(libroService.consultarCatalogo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.consultarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> editar(@PathVariable Long id,
                                         @RequestBody Libro datos) {
        return ResponseEntity.ok(libroService.editarLibro(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBaja(@PathVariable Long id) {
        libroService.darDeBajaLibro(id);
        return ResponseEntity.noContent().build();
    }
}