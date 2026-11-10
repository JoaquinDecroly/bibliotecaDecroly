package es.decroly.biblioteca.services.Biblioteca;

import es.decroly.biblioteca.dto.BibliotecaInfoDto;

/**
 * Interfaz que define las operaciones de servicio para la información de la biblioteca.
 * Proporciona los datos generales configurados en {@code application.properties}.
 */
public interface BibliotecaService {

    /**
     * Obtiene la información general de la biblioteca.
     *
     * @return DTO con el nombre de la biblioteca, el máximo de préstamos y los días de préstamo
     */
    BibliotecaInfoDto obtenerInfoBiblio();
}
