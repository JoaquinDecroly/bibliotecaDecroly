package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;

import java.util.List;

public interface LibroService {
    List<Libro> listarLibros();

    Libro buscarPorId(Long id);

    List<Libro> listarLibrosDisponibles();

    List<Libro> obtenerLibrosDeCategoria(Long id);

    List<Libro> listarLibrosDeAutor(String autor);

    Libro crear(LibroPeticionDto libro);

    Libro modificar(Long id, LibroPeticionDto libro);

    void eliminar(Long id);



}
