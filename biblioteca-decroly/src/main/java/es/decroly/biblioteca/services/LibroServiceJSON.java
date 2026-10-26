package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Libro;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// ================== LibroServiceJSON ==================
// Servicio de libros que carga los datos desde un fichero JSON
// Hereda de LibroServiceBase (ahí está la lógica: listar, crear, modificar, borrar...)

// CONSTRUCTOR
// -> Recibe el ObjectMapper (para leer JSON) y BibliotecaProperties (configuración)
// -> Carga los libros del JSON y se los pasa al padre con super(...)

// cargar(mapper)
// -> Busca "libros.json" en resources (ClassPathResource)
// -> Abre el fichero con try-with-resources (se cierra solo)
// -> mapper.readValue(...) convierte el JSON en un array Libro[]
// -> List.of(...) pasa el array a lista
////////////////////////////////////////////////////////////////////////

public class LibroServiceJSON extends LibroServiceBase {
    public LibroServiceJSON(ObjectMapper mapper, BibliotecaProperties properties) throws IOException {
        super((ArrayList<Libro>) cargar(mapper), properties);
    }

    private static List<Libro> cargar(ObjectMapper mapper) throws IOException {
        ClassPathResource fichero = new ClassPathResource("libros.json");
        try (InputStream entrada = fichero.getInputStream()) {
            return List.of(mapper.readValue(entrada, Libro[].class));
        }
    }
}
