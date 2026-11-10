package es.decroly.biblioteca.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Propiedades de configuración propias de la biblioteca, enlazadas desde
 * {@code application.properties} bajo el prefijo {@code biblioteca}.
 * Si una propiedad no está en el fichero, se usa el valor de {@code @DefaultValue}.
 *
 * @param nombre       nombre de la biblioteca
 * @param maxPrestamos número máximo de préstamos permitidos
 * @param diasPrestamo duración de un préstamo en días
 */
@ConfigurationProperties(prefix="biblioteca")
public record BibliotecaProperties( @DefaultValue("Biblioteca Decroly") String nombre,
                                    @DefaultValue("3") int maxPrestamos,
                                    @DefaultValue("15") int diasPrestamo) {

}
