package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.services.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categorías", description = "Gestión de categorías")
@RequestMapping("api/categorias")
@RestController
public class CategoriaRestController {
    private final CategoriaService categoriaService;

    public CategoriaRestController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
}
