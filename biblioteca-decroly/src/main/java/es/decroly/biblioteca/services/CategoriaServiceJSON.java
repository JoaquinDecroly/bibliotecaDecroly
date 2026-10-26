package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Categoria;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// ================== CategoriaServiceJSON ==================
// Servicio de categorías que carga los datos desde un fichero JSON
// Hereda de CategoriaServiceBase (ahí está la lógica: listar, crear, modificar, borrar...)

// CONSTRUCTOR
// -> Recibe el ObjectMapper (para leer JSON) y BibliotecaProperties (configuración)
// -> Carga las categorías del JSON y se las pasa al padre con super(...)

// cargar(mapper)
// -> Busca "categorias.json" en resources (ClassPathResource)
// -> Abre el fichero con try-with-resources (se cierra solo)
// -> mapper.readValue(...) convierte el JSON en un array Categoria[]
// -> List.of(...) pasa el array a lista
////////////////////////////////////////////////////////////////

public class CategoriaServiceJSON extends CategoriaServiceBase {
    /**
     * Constructor que inicializa el servicio cargando categorías desde un fichero JSON.
     *
     * @param mapper ObjectMapper para leer el JSON
     * @param properties Propiedades de configuración de la biblioteca
     * @throws IOException si hay error al leer el fichero JSON
     */
    public CategoriaServiceJSON(ObjectMapper mapper, BibliotecaProperties properties) throws IOException {
            super((ArrayList<Categoria>) cargar(mapper), properties);
    }

    /**
     * Carga la lista de categorías desde el fichero JSON.
     *
     * @param mapper ObjectMapper para leer el JSON
     * @return Lista de categorías cargadas desde el fichero
     * @throws IOException si hay error al leer el fichero
     */
    private static List<Categoria> cargar(ObjectMapper mapper) throws IOException {
        ClassPathResource fichero = new ClassPathResource("categorias.json");
        try (InputStream entrada = fichero.getInputStream()) {
            return List.of(mapper.readValue(entrada, Categoria[].class));
        }
    }
}
