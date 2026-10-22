package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> listarLibros();

    Libro buscarPorId(Long id);

    List<Libro> listarLibrosDisponibles();

    List<Libro> obtenerLibrosDeCategoria(Long id);

    List<Libro> listarLibrosDeAutor(String autor);

    Libro crear(Libro libro);

    Libro modificar(Long id, Libro libro);

    void eliminar(Long id);



}
