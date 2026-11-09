package es.decroly.biblioteca.controllers;

// ================== AUXILIARES ==================
// @RestController -> Clase que devuelve JSON
// @PathVariable   -> Valor de la ruta      -> /usuarios/{id}
// @RequestParam   -> Valor tras el ?       -> /buscar?nombre=Ana
// @RequestBody    -> JSON del body a objeto Java (POST/PUT/PATCH)
// @ResponseStatus -> Fija el código HTTP de respuesta

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Biblioteca", description = "Gestión de libros y categorías")
@RequestMapping("api/biblioteca")
@RestController
public class BibliotecaRestController {
    //----GET PARA OBTENER TODAS LAS CATEGORÍAS-----
    @Operation(summary = "Información de la biblioteca")
    @GetMapping("/info")
    public String obtenerInfoBiblioteca(){return "Información de la biblioteca";};
}
