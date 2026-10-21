package es.decroly.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
