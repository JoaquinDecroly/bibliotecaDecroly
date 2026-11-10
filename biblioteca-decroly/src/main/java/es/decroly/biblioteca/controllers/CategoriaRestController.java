package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.services.categoria.CategoriaService;
import es.decroly.biblioteca.services.libro.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ================== AUXILIARES ==================
// @RestController -> Clase que devuelve JSON
// @RequiredArgsConstructor -> Lombok crea el constructor con los atributos final (inyección por constructor)
// @PathVariable   -> Valor de la ruta      -> /categorias/{id}
// @RequestParam   -> Valor tras el ?       -> /libros?autor=Ana
// @RequestBody    -> JSON del body a objeto Java (POST/PUT/PATCH)
// @ResponseStatus -> Fija el código HTTP de respuesta

@Tag(name = "Categorías", description = "Gestión de categorías")
@RequestMapping("api/categorias")
@RestController
@RequiredArgsConstructor
public class CategoriaRestController {
    /** Servicio de categorías para la lógica de negocio */
    private final CategoriaService categoriaService;
    /** Servicio de libros para obtener los libros de una categoría */
    private final LibroService libroService;

    // ================== ENDPOINTS CATEGORÍAS ==================
    // Ruta base: /api/categorias

    // GET    /api/categorias              -> Lista TODAS las categorías
    // GET    /api/categorias/{id}         -> Obtiene UNA categoría por id   | @PathVariable id
    // GET    /api/categorias/{id}/libros  -> Lista los libros de una categoría | @PathVariable id
    // POST   /api/categorias              -> CREA una categoría             | @RequestBody CategoriaPeticionDto
    // PUT    /api/categorias/{id}         -> MODIFICA una categoría por id  | @PathVariable + @RequestBody
    // DELETE /api/categorias/{id}         -> BORRA una categoría por id     | @PathVariable id

    // Respuestas: 200 -> OK | 201 -> Creado | 204 -> Borrado | 400 -> Datos no válidos | 404 -> No encontrado | 409 -> Nombre repetido

    /**
     * Endpoint para listar todas las categorías del sistema.
     * GET /api/categorias
     *
     * @return Lista completa de categorías
     */
    //----GET PARA OBTENER TODAS LAS CATEGORÍAS-----
    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Esta es la colección de categorías")
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    /**
     * Endpoint para obtener una categoría por su ID.
     * GET /api/categorias/{id}
     *
     * @param id Identificador de la categoría
     * @return Categoría encontrada
     */
    //----GET PARA OBTENER CATEGORÍA POR ID----
    @Operation(summary = "Obtener una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    /**
     * Endpoint para listar libros de una categoría específica.
     * GET /api/categorias/{id}/libros
     *
     * @param id Identificador de la categoría
     * @return Lista de libros de la categoría
     */
    //----GET PARA OBTENER TODOS LIBROS POR CATEGORIA-----
    @Operation(summary = "Obtener libros por categoría")
    @ApiResponse(responseCode = "200", description = "Libros de la categoría (lista vacía si no tiene ninguno)")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}/libros")
    public List<Libro> listarLibrosCategoria(@PathVariable Long id){
        return libroService.obtenerLibrosDeCategoria(id);
    }

    /**
     * Endpoint para crear una nueva categoría.
     * POST /api/categorias
     *
     * @param dto DTO con los datos de la categoría a crear
     * @return Categoría creada
     */
    //-----POST PARA AÑADIR CATEGORIA-----
    @Operation(summary = "Añadir una categoría")
    @ApiResponse(responseCode = "201", description = "Categoría añadida exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos no válidos (nombre vacío o de más de 50 caracteres)",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "409", description = "Ya existe una categoría con ese nombre",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria add(@RequestBody CategoriaPeticionDto dto) {
        return categoriaService.crear(dto);
    }

    /**
     * Endpoint para modificar una categoría existente.
     * PUT /api/categorias/{id}
     *
     * @param id Identificador de la categoría a modificar
     * @param dto DTO con los nuevos datos de la categoría
     * @return Categoría modificada
     */
    //----PUT PARA MODIFICAR CATEGORIA POR ID----
    @Operation(summary = "Modificar una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada y modificada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos no válidos (nombre vacío o de más de 50 caracteres)",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada, y por ende, no modificada",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "409", description = "Otra categoría ya tiene ese nombre",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @PutMapping("/{id}")
    public Categoria modProd(@PathVariable Long id, @RequestBody CategoriaPeticionDto dto) {
        return categoriaService.modificar(id, dto);
    }

    /**
     * Endpoint para eliminar una categoría.
     * DELETE /api/categorias/{id}
     *
     * @param id Identificador de la categoría a eliminar
     */
    //----DELETE PARA BORRAR CATEGORIA POR ID----
    @Operation(summary = "Borrar una categoría por id")
    @ApiResponse(responseCode = "204", description = "Categoría encontrada y suprimida")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada, y por ende, no suprimida",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }
}
