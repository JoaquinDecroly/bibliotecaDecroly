package es.decroly.biblioteca.domain;

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
public class Libro {
    /** Identificador único del libro */
    private Long id;
    /** ISBN (International Standard Book Number) del libro */
    private String isbn;
    /** Título del libro */
    private String titulo;
    /** Autor del libro */
    private String autor;
    /** Año de publicación del libro */
    private String anioPublicacion;
    /** Número total de ejemplares del libro */
    private int ejemplares;
    /** Número de ejemplares disponibles para préstamo */
    private int disponibles;
    /** Identificador de la categoría a la que pertenece el libro */
    private Long categoriaId;

    /**
     * Constructor que crea un libro con ID especificado.
     * Inicializa ejemplares y disponibles a 1, y sin categoría.
     *
     * @param nId Identificador del libro
     * @param titulo Título del libro
     * @param isbn ISBN del libro
     * @param autor Autor del libro
     * @param anioPublicacion Año de publicación
     */
    public Libro(Long nId, String titulo, String isbn, String autor, String anioPublicacion) {
        this.id = nId;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplares = 1;
        this.disponibles = 1;
        this.categoriaId = null;
    }

    /**
     * Constructor que crea un libro sin ID.
     * Inicializa ejemplares y disponibles a 1, y sin categoría.
     *
     * @param isbn ISBN del libro
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anioPublicacion Año de publicación
     */
    public Libro(String isbn, String titulo, String autor, String anioPublicacion) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplares = 1;
        this.disponibles = 1;
        this.categoriaId = null;
    }

//    public Libro(Long nuevoId, String isbn, String titulo, String autor, String anioPublicacion, AtomicReference<Long> categoriaId) {
//        this.id = nuevoId;
//        this.titulo = titulo;
//        this.isbn = isbn;
//        this.autor = autor;
//        this.anioPublicacion = anioPublicacion;
//        this.ejemplares = 1;
//        this.disponibles = 1;
//        this.categoriaId = categoriaId.get();
//    }

    /**
     * Constructor que crea un libro con ID y categoría especificados.
     * Inicializa ejemplares y disponibles a 1.
     *
     * @param nuevoId Identificador del libro
     * @param isbn ISBN del libro
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anioPublicacion Año de publicación
     * @param categoriaId Identificador de la categoría
     */
    public Libro(Long nuevoId, String isbn, String titulo, String autor, String anioPublicacion, Long categoriaId) {
        this.id = nuevoId;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplares = 1;
        this.disponibles = 1;
        this.categoriaId = categoriaId;
    }

    /**
     * Constructor que crea un libro sin ID pero con categoría.
     * Inicializa ejemplares y disponibles a 1.
     *
     * @param isbn ISBN del libro
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param anioPublicacion Año de publicación
     * @param categoriaId Identificador de la categoría
     */
    public Libro(String isbn, String titulo, String autor, String anioPublicacion, Long categoriaId) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.ejemplares = 1;
        this.disponibles = 1;
        this.categoriaId = categoriaId;
    }
}
