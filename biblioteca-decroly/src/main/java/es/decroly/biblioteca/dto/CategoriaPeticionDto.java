package es.decroly.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la creación y modificación de categorías.
 * Contiene los datos necesarios para crear o actualizar una categoría en el sistema.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
public class CategoriaPeticionDto {
    /** Nombre de la categoría */
    private String nombre;
    /** Descripción de la categoría */
    private String descripcion;
}
