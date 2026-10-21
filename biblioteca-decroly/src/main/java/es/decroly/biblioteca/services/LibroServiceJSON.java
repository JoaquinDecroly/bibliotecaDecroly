package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LibroServiceJSON extends LibroServiceBase {
    public LibroServiceJSON(ObjectMapper mapper) throws IOException {
        super((ArrayList<Libro>) cargar(mapper));
    }

    private static List<Libro> cargar(ObjectMapper mapper) throws IOException {
        ClassPathResource fichero = new ClassPathResource("libros.json");
        try (InputStream entrada = fichero.getInputStream()) {
            return List.of(mapper.readValue(entrada, Libro[].class));
        }
    }
}
