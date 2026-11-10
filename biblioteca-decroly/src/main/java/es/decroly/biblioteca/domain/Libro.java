package es.decroly.biblioteca.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un libro en el sistema de biblioteca.
 * Contiene información sobre el libro como ISBN, título, autor, año de publicación,
 * número de ejemplares totales y disponibles, y la categoría a la que pertenece.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
@Schema(description = "Libro de la biblioteca")
public class Libro {
    /** Identificador único del libro */
    @Schema(description = "Identificador del libro", example = "41")
    private Long id;
    /** ISBN (International Standard Book Number) del libro */
    @Schema(description = "ISBN de 13 cifras", example = "9788491050292")
    private String isbn;
    /** Título del libro */
    @Schema(description = "Título del libro", example = "Don Quijote de la Mancha")
    private String titulo;
    /** Autor del libro */
    @Schema(description = "Autor del libro", example = "Miguel de Cervantes")
    private String autor;
    /** Año de publicación del libro */
    @Schema(description = "Año de publicación", example = "1605")
    private Integer anioPublicacion;
    /** Número total de ejemplares del libro */
    @Schema(description = "Número total de ejemplares", example = "2")
    private int ejemplares;
    /** Número de ejemplares disponibles para préstamo */
    @Schema(description = "Ejemplares disponibles para préstamo", example = "2")
    private int disponibles;
    /** Identificador de la categoría a la que pertenece el libro */
    @Schema(description = "Id de la categoría a la que pertenece", example = "16")
    private Long categoriaId;

    /**
     * Constructor que crea un libro con ID y categoría especificados.
     * Inicializa ejemplares y disponibles a 1 (se usa para los libros de prueba).
     *
     * @param nuevoId Identificador del libro
     * @param isbn ISBN del libro
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anioPublicacion Año de publicación
     * @param categoriaId Identificador de la categoría
     */
    public Libro(Long nuevoId, String isbn, String titulo, String autor, Integer anioPublicacion, Long categoriaId) {
        this.id = nuevoId;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplares = 1;
        this.disponibles = 1;
        this.categoriaId = categoriaId;
    }
}
