package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> listarProductos();

    Libro buscarPorId(Long id);

    Libro crear(Libro libro);

    Libro modificar(Long id, Libro libro);

    void eliminar(Long id);



}
