package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.models.*;
import com.biblioteca.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroService libroService;
    private final UsuarioService usuarioService;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           LibroService libroService,
                           UsuarioService usuarioService) {
        this.prestamoRepository = prestamoRepository;
        this.libroService = libroService;
        this.usuarioService = usuarioService;
    }

    public Prestamo prestarLibro(Long usuarioId, Long libroId,
                                  LocalDate fechaDevolucionEsperada) {
        Usuario usuario = usuarioService.consultarPorId(usuarioId);
        Libro libro = libroService.consultarPorId(libroId);

        if (!usuario.isActivo()) {
            throw new RuntimeException("El usuario está dado de baja.");
        }
        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new RuntimeException("El libro no está disponible para préstamo.");
        }

        libro.setEstado(EstadoLibro.PRESTADO);

        Prestamo prestamo = new Prestamo(usuario, libro, fechaDevolucionEsperada);
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> consultarTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> consultarPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
}