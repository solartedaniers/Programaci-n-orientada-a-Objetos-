package com.biblioteca.biblioteca.service;


import com.biblioteca.biblioteca.models.EstadoLibro;
import com.biblioteca.biblioteca.models.Libro;
import com.biblioteca.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro registrarLibro(Libro libro) {
        if (libroRepository.findByIsbn(libro.getIsbn()).isPresent()) {
            throw new RuntimeException("Ya existe un libro con ese ISBN.");
        }
        return libroRepository.save(libro);
    }

    public List<Libro> consultarCatalogo() {
        return libroRepository.findByActivoTrue();
    }

    public Libro consultarPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("El ID del libro no puede ser nulo.");
        }

        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado."));
    }

    public Libro editarLibro(Long id, Libro datos) {
        Libro libro = consultarPorId(id);
        if (!libro.isActivo()) {
            throw new RuntimeException("No se puede editar un libro dado de baja.");
        }
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setEditorial(datos.getEditorial());
        libro.setGenero(datos.getGenero());
        return libroRepository.save(libro);
    }

    public void darDeBajaLibro(Long id) {
        Libro libro = consultarPorId(id);
        if (libro.getEstado() == EstadoLibro.PRESTADO) {
            throw new RuntimeException("No se puede dar de baja un libro actualmente prestado.");
        }
        libro.setActivo(false);
        libro.setEstado(EstadoLibro.DADO_DE_BAJA);
        libroRepository.save(libro);
    }
}