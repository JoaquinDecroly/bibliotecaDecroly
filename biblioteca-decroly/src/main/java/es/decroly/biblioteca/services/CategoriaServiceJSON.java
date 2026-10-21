package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CategoriaServiceJSON extends CategoriaServiceBase {
    public CategoriaServiceJSON(ObjectMapper mapper) throws IOException {
            super((ArrayList<Categoria>) cargar(mapper));
    }

    private static List<Categoria> cargar(ObjectMapper mapper) throws IOException {
        ClassPathResource fichero = new ClassPathResource("categorias.json");
        try (InputStream entrada = fichero.getInputStream()) {
            return List.of(mapper.readValue(entrada, Categoria[].class));
        }
    }
}
