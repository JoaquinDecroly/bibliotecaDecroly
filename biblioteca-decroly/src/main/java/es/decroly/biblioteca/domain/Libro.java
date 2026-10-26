package es.decroly.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private String anioPublicacion;
    private int ejemplares;
    private int disponibles;
    private Long categoriaId;

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
