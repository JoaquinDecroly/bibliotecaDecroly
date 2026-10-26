package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;

import java.util.List;

/**
 * Interfaz que define las operaciones de servicio para la gestión de categorías.
 * Proporciona métodos para listar, buscar, crear, modificar y eliminar categorías de libros.
 */
public interface CategoriaService {
    /**
     * Lista todas las categorías del sistema.
     *
     * @return Lista completa de categorías
     */
    List<Categoria> listarCategorias();

    /**
     * Busca una categoría por su identificador.
     *
     * @param id Identificador de la categoría
     * @return Categoría encontrada
     * @throws NotFoundException si no existe una categoría con ese ID
     */
    Categoria buscarPorId(Long id);

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param categoria DTO con los datos de la categoría a crear
     * @return Categoría creada con su ID asignado
     * @throws BadRequestException si los datos son inválidos
     */
    Categoria crear(CategoriaPeticionDto categoria);

    /**
     * Modifica una categoría existente.
     *
     * @param id Identificador de la categoría a modificar
     * @param categoria DTO con los nuevos datos de la categoría
     * @return Categoría modificada
     * @throws NotFoundException si no existe la categoría
     * @throws BadRequestException si los datos son inválidos
     */
    Categoria modificar(Long id, CategoriaPeticionDto categoria);

    /**
     * Elimina una categoría del sistema.
     *
     * @param id Identificador de la categoría a eliminar
     * @throws NotFoundException si no existe la categoría
     */
    void eliminar(Long id);
}
