package es.decroly.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
                                title = "Biblioteca Decroly",
                                version = "1.0.0",
                                description = "API para gestionar categorías y libros"))
public class OpenApiConfig {

}
