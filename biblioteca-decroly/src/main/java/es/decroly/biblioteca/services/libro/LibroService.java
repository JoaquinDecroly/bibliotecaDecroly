package es.decroly.biblioteca.services.libro;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;

import java.util.List;

/**
 * Interfaz que define las operaciones de servicio para la gestión de libros.
 * Proporciona métodos para listar, buscar, crear, modificar y eliminar libros,
 * así como consultas especializadas como libros disponibles, por autor o por categoría.
 */
public interface LibroService {
    /**
     * Lista todos los libros del sistema.
     *
     * @return Lista completa de libros
     */
    List<Libro> listarLibros();

    /**
     * Busca un libro por su identificador.
     *
     * @param id Identificador del libro
     * @return Libro encontrado
     * @throws NotFoundException si no existe un libro con ese ID
     */
    Libro buscarPorId(Long id);

    /**
     * Lista los libros que tienen ejemplares disponibles para préstamo.
     *
     * @return Lista de libros disponibles
     */
    List<Libro> listarLibrosDisponibles();

    /**
     * Obtiene los libros que pertenecen a una categoría específica.
     *
     * @param id Identificador de la categoría
     * @return Lista de libros de la categoría
     */
    List<Libro> obtenerLibrosDeCategoria(Long id);

    /**
     * Lista los libros escritos por un autor específico.
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     *
     * @param autor Nombre del autor
     * @return Lista de libros del autor
     */
    List<Libro> listarLibrosDeAutor(String autor);

    /**
     * Crea un nuevo libro en el sistema.
     *
     * @param libro DTO con los datos del libro a crear
     * @return Libro creado con su ID asignado
     * @throws BadRequestException si los datos son inválidos
     */
    Libro crear(LibroPeticionDto libro);

    /**
     * Modifica un libro existente.
     *
     * @param id Identificador del libro a modificar
     * @param libro DTO con los nuevos datos del libro
     * @return Libro modificado
     * @throws NotFoundException si no existe el libro
     * @throws BadRequestException si los datos son inválidos
     */
    Libro modificar(Long id, LibroPeticionDto libro);

    /**
     * Elimina un libro del sistema.
     *
     * @param id Identificador del libro a eliminar
     * @throws NotFoundException si no existe el libro
     */
    void eliminar(Long id);



}
