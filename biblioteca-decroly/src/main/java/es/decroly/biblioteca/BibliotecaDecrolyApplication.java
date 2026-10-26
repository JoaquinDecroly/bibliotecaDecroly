package es.decroly.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Punto de entrada de la aplicación Biblioteca Decroly.
 *
 * <p>Inicia Spring Boot y habilita el escaneo de propiedades de configuración.</p>
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class BibliotecaDecrolyApplication {

    /**
     * Inicia la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaDecrolyApplication.class, args);
    }

}
