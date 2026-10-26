package es.decroly.biblioteca.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//mirar en application.properties y mirar por el prefijo "biblioteca"
@ConfigurationProperties(prefix="biblioteca")
public record BibliotecaProperties( String nombre,
                                    int maxPrestamos,
                                    int diasPrestamo) {

}
