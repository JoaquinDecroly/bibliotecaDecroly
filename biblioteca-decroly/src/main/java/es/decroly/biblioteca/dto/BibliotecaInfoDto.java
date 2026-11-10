package es.decroly.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) con la información general de la biblioteca.
 * Contiene los valores configurados en {@code application.properties}.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
@Schema(description = "Información general de la biblioteca")
public class BibliotecaInfoDto {
    /** Nombre de la biblioteca */
    @Schema(description = "Nombre de la biblioteca", example = "Biblioteca Decroly")
    private String nombre;
    /** Número máximo de préstamos permitidos */
    @Schema(description = "Número máximo de préstamos permitidos", example = "3")
    private int maxPrestamos;
    /** Duración de un préstamo en días */
    @Schema(description = "Duración de un préstamo en días", example = "15")
    private int diasPrestamo;
}

