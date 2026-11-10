package es.decroly.biblioteca.services.Biblioteca;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.dto.BibliotecaInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de información de la biblioteca.
 * Lee los valores de BibliotecaProperties, que Spring carga desde {@code application.properties}.
 * Anotada con @Service para que Spring la detecte e inyecte automáticamente.
 */
@Service
@RequiredArgsConstructor //CONSTRUCTOR CON LOS ATRIBUTOS FINAL (INYECCIÓN POR CONSTRUCTOR)
public class BibliotecaServiceBase implements BibliotecaService {
    /** Propiedades de configuración de la biblioteca */
    private final BibliotecaProperties properties;

    /**
     * Obtiene la información general de la biblioteca.
     *
     * @return DTO con el nombre de la biblioteca, el máximo de préstamos y los días de préstamo
     */
    //-----METODO OBTENER INFORMACIÓN DE LA BIBLIOTECA----
    @Override
    public BibliotecaInfoDto obtenerInfoBiblio() {
        return new BibliotecaInfoDto(properties.nombre(), properties.maxPrestamos(), properties.diasPrestamo());
    }
}
