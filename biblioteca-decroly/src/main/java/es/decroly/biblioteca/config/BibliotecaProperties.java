package es.decroly.biblioteca.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propiedades de configuración propias de la biblioteca, enlazadas desde
 * {@code application.properties} bajo el prefijo {@code biblioteca}.
 *
 * @param nombre       nombre de la biblioteca
 * @param maxPrestamos número máximo de préstamos permitidos
 * @param diasPrestamo duración de un préstamo en días
 */
@ConfigurationProperties(prefix="biblioteca")
public record BibliotecaProperties( String nombre,
                                    int maxPrestamos,
                                    int diasPrestamo) {

}
