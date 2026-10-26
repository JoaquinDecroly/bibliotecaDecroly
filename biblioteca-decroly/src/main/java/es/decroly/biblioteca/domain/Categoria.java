package es.decroly.biblioteca.domain;

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
public class Categoria {
    /** Identificador único de la categoría */
    private Long id;
    /** Nombre de la categoría */
    private String nombre;
    /** Descripción detallada de la categoría */
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
