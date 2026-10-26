package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;

import java.util.List;

public interface LibroService {
    //----LISTAR LIBROS-----
    List<Libro> listarLibros();

    //-----BUSCAR POR ID-----
    Libro buscarPorId(Long id);

    //-------LISTAR LIBROS DISPONIBLES------
    List<Libro> listarLibrosDisponibles();

    //-----OBTENER LIBROS DADA LA CATEGORIA (POR ID)------
    List<Libro> obtenerLibrosDeCategoria(Long id);

    //-----LISTAR LIBROS DE AUTOR (DADO EL NOMBRE)----
    List<Libro> listarLibrosDeAutor(String autor);

    //------CREAR LIBRO----
    Libro crear(LibroPeticionDto libro);

    //----MODIFICAR LIBRO-----
    Libro modificar(Long id, LibroPeticionDto libro);

    //-----ELIMINAR LIBRO-----
    void eliminar(Long id);



}
