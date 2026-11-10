package es.decroly.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para la creación y modificación de libros.
 * Contiene los datos necesarios para crear o actualizar un libro en el sistema.
 * No incluye el id ni los disponibles: los controla el servicio.
 */
//-----ANOTACIONES LOMBOK----
@Getter //OBTENER DATOS
@Setter //MODIFICAR DATOS
@NoArgsConstructor //CONSTRUCTOR SIN PARÁMETROS
@AllArgsConstructor//CONSTRUCTOR CON PARÁMETROS
@Schema(description = "Datos para crear o modificar un libro")
public class LibroPeticionDto {
    /** ISBN del libro */
    @Schema(description = "ISBN de 13 cifras, no se puede repetir", example = "9788491050292")
    private String isbn;
    /** Título del libro */
    @Schema(description = "Título del libro (obligatorio)", example = "Don Quijote de la Mancha")
    private String titulo;
    /** Autor del libro */
    @Schema(description = "Autor del libro (obligatorio)", example = "Miguel de Cervantes")
    private String autor;
    /** Año de publicación del libro */
    @Schema(description = "Año de publicación", example = "1605")
    private Integer anioPublicacion;
    /** Número de ejemplares del libro */
    @Schema(description = "Número de ejemplares (mínimo 1)", example = "2")
    private int ejemplares;
    /** Identificador de la categoría del libro */
    @Schema(description = "Id de una categoría que exista", example = "16")
    private Long categoriaId;
}