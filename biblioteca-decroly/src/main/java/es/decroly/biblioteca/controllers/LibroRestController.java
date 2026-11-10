package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.services.libro.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
// @PathVariable   -> Valor de la ruta      -> /libros/{id}
// @RequestParam   -> Valor tras el ?       -> /libros?autor=Ana
// @RequestBody    -> JSON del body a objeto Java (POST/PUT/PATCH)
// @ResponseStatus -> Fija el código HTTP de respuesta

@Tag(name = "Libros", description = "Gestión de libros")
@RequestMapping("api/libros")
@RestController
@RequiredArgsConstructor
public class LibroRestController {
    /** Servicio de libros para la lógica de negocio */
    private final LibroService libroService;

    // ================== ENDPOINTS LIBROS ==================
    // Ruta base: /api/libros

    // GET    /api/libros                -> Lista TODOS los libros; con ?autor= filtra por autor | @RequestParam autor
    // GET    /api/libros/disponibles    -> Lista libros DISPONIBLES
    // GET    /api/libros/{id}           -> Obtiene UN libro por id       | @PathVariable id
    // POST   /api/libros                -> CREA un libro                 | @RequestBody LibroPeticionDto
    // PUT    /api/libros/{id}           -> MODIFICA un libro por id      | @PathVariable + @RequestBody
    // DELETE /api/libros/{id}           -> BORRA un libro por id         | @PathVariable id

    // Respuestas: 200 -> OK | 201 -> Creado | 204 -> Borrado | 400 -> Datos no válidos | 404 -> No encontrado | 409 -> ISBN repetido

    /**
     * Endpoint para listar los libros con algún ejemplar disponible.
     * GET /api/libros/disponibles
     *
     * @return Lista de libros disponibles
     */
    //----GET PARA OBTENER TODOS LOS LIBROS DISPONIBLES-----
    @Operation(summary = "Listar libros disponibles")
    @ApiResponse(responseCode = "200", description = "Libros con algún ejemplar disponible")
    @GetMapping("/disponibles")
    public List<Libro> listarLibrosDisponibles(){
        return libroService.listarLibrosDisponibles();
    }

    /**
     * Endpoint para listar todos los libros del sistema.
     * Con el parámetro autor filtra por autor (contiene, sin distinguir mayúsculas).
     * GET /api/libros
     * GET /api/libros?autor=texto
     *
     * @param autor Texto a buscar en el autor (opcional)
     * @return Lista de libros
     */
    //----GET PARA OBTENER TODOS LOS LIBROS (O LOS DE UN AUTOR)-------
    @Operation(summary = "Listar todos los libros", description = "Con ?autor= filtra por autor (contiene, sin distinguir mayúsculas)")
    @ApiResponse(responseCode = "200", description = "Mostrando los libros de la colección")
    @GetMapping
    public List<Libro> listarLibros(
            @Parameter(description = "Texto que debe contener el autor", example = "king")
            @RequestParam(required = false) String autor) {
        return libroService.listarLibrosDeAutor(autor);
    }

    /**
     * Endpoint para obtener un libro por su ID.
     * GET /api/libros/{id}
     *
     * @param id Identificador del libro
     * @return Libro encontrado
     */
    //----GET PARA OBTENER UN LIBRO POR ID-----
    @Operation(summary = "Obtener un Libro por id")
    @ApiResponse(responseCode = "200", description = "Libro encontrado")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    /**
     * Endpoint para crear un nuevo libro.
     * POST /api/libros
     *
     * @param dto DTO con los datos del libro a crear
     * @return Libro creado
     */
    //---POST PARA CREAR UN LIBRO----
    @Operation(summary = "Añadir un libro")
    @ApiResponse(responseCode = "201", description = "Libro añadido exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos no válidos (ISBN sin 13 cifras, falta título o autor, ejemplares menor que 1 o categoría que no existe)",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "409", description = "Ya existe un libro con ese ISBN",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro add(@RequestBody LibroPeticionDto dto) {
        return libroService.crear(dto);
    }

    /**
     * Endpoint para modificar un libro existente.
     * PUT /api/libros/{id}
     *
     * @param id Identificador del libro a modificar
     * @param dto DTO con los nuevos datos del libro
     * @return Libro modificado
     */
    //-----PUT PARA MODIFICAR UN LIBRO POR ID-----
    @Operation(summary = "Modificar un Libro por id")
    @ApiResponse(responseCode = "200", description = "Libro encontrado y modificado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos no válidos (ISBN sin 13 cifras, falta título o autor, ejemplares menor que 1 o categoría que no existe)",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Libro no encontrado, y por ende, no modificado",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "409", description = "Otro libro ya tiene ese ISBN",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @PutMapping("/{id}")
    public Libro modProd(@PathVariable Long id, @RequestBody LibroPeticionDto dto) {
        return libroService.modificar(id, dto);
    }

    /**
     * Endpoint para eliminar un libro.
     * DELETE /api/libros/{id}
     *
     * @param id Identificador del libro a eliminar
     */
    //------DELETE PARA BORRAR LIBRO POR ID-----
    @Operation(summary = "Borrar un Libro por id")
    @ApiResponse(responseCode = "204", description = "Libro encontrado y eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado, y por ende, no eliminado",
            content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
