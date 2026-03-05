package com.biblioteca.biblioteca.controller;


import com.biblioteca.biblioteca.models.Prestamo;
import com.biblioteca.biblioteca.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> prestar(@RequestBody Map<String, String> body) {
        Long usuarioId = Long.parseLong(body.get("usuarioId"));
        Long libroId = Long.parseLong(body.get("libroId"));
        LocalDate fechaDevolucion = LocalDate.parse(body.get("fechaDevolucionEsperada"));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prestamoService.prestarLibro(usuarioId, libroId, fechaDevolucion));
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> todos() {
        return ResponseEntity.ok(prestamoService.consultarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> porUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(prestamoService.consultarPorUsuario(usuarioId));
    }
}