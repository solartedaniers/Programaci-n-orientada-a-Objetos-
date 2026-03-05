package com.biblioteca.biblioteca.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    public Prestamo() {
        this.estado = EstadoPrestamo.ACTIVO;
        this.fechaPrestamo = LocalDate.now();
    }

    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaDevolucionEsperada) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.estado = EstadoPrestamo.ACTIVO;
    }

    public Long getId() { return id; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucionEsperada() { return fechaDevolucionEsperada; }
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
    public EstadoPrestamo getEstado() { return estado; }
    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }

    public void setFechaDevolucionReal(LocalDate fecha) { this.fechaDevolucionReal = fecha; }
    public void setEstado(EstadoPrestamo estado) { this.estado = estado; }
}