package es.decroly.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroPeticionDto {
    private String isbn;
    private String titulo;
    private String autor;
    private String anioPublicacion;
    private int ejemplares;
    private Long categoriaId;
}
