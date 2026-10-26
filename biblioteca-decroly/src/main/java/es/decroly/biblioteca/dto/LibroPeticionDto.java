package es.decroly.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la creación y modificación de libros.
 * Contiene los datos necesarios para crear o actualizar un libro en el sistema.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
public class LibroPeticionDto {
    /** ISBN del libro */
    private String isbn;
    /** Título del libro */
    private String titulo;
    /** Autor del libro */
    private String autor;
    /** Año de publicación del libro */
    private String anioPublicacion;
    /** Número de ejemplares del libro */
    private int ejemplares;
    /** Identificador de la categoría del libro */
    private Long categoriaId;
}
