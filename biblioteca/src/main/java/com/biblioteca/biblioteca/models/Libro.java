package com.biblioteca.biblioteca.models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    private String editorial;
    private int anioPublicacion;
    private String genero;

    @Enumerated(EnumType.STRING)
    private EstadoLibro estado;

    private boolean activo;

    public Libro() {
        this.estado = EstadoLibro.DISPONIBLE;
        this.activo = true;
    }

    public Libro(String isbn, String titulo, String autor,
                 String editorial, int anioPublicacion, String genero) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.estado = EstadoLibro.DISPONIBLE;
        this.activo = true;
    }

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getEditorial() { return editorial; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public String getGenero() { return genero; }
    public EstadoLibro getEstado() { return estado; }
    public boolean isActivo() { return activo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setEstado(EstadoLibro estado) { this.estado = estado; }
    public void setActivo(boolean activo) { this.activo = activo; }
}