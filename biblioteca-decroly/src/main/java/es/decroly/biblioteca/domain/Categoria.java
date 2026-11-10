package es.decroly.biblioteca.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa una categoría de libros en el sistema de biblioteca.
 * Las categorías permiten clasificar y organizar los libros por temas o géneros.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
@Schema(description = "Categoría de libros")
public class Categoria {
    /** Identificador único de la categoría */
    @Schema(description = "Identificador de la categoría", example = "16")
    private Long id;
    /** Nombre de la categoría */
    @Schema(description = "Nombre de la categoría", example = "Literatura clásica")
    private String nombre;
    /** Descripción detallada de la categoría */
    @Schema(description = "Descripción de la categoría", example = "Categoría de libros de literatura clásica")
    private String descripcion;

    /**
     * Constructor que crea una categoría sin ID.
     *
     * @param nombre Nombre de la categoría
     * @param descripcion Descripción de la categoría
     */
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
