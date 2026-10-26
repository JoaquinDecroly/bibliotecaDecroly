package es.decroly.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configura la información general que se muestra en la documentación OpenAPI
 * de la API de Biblioteca Decroly.
 */
@Configuration
@OpenAPIDefinition(info = @Info( //-----DESCRIPCIÓN SWAGGER-----
                                title = "Biblioteca Decroly",
                                version = "1.0.0",
                                description = "API para gestionar categorías y libros"))
public class OpenApiConfig {

}
