package es.decroly.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos para crear o modificar una categoría")
public class CategoriaPeticionDto {
    /** Nombre de la categoría */
    @Schema(description = "Nombre de la categoría (obligatorio, máximo 50 caracteres, no se puede repetir)",
            example = "Teatro")
    private String nombre;
    /** Descripción de la categoría */
    @Schema(description = "Descripción de la categoría", example = "Categoría de obras de teatro")
    private String descripcion;
}
