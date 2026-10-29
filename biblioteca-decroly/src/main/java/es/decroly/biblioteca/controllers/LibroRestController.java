package es.decroly.biblioteca.controllers;


import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.services.categoria.CategoriaService;
import es.decroly.biblioteca.services.libro.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// ================== AUXILIARES ==================
    // @RestController -> Clase que devuelve JSON
    // @PathVariable   -> Valor de la ruta      -> /usuarios/{id}
    // @RequestParam   -> Valor tras el ?       -> /buscar?nombre=Ana
    // @RequestBody    -> JSON del body a objeto Java (POST/PUT/PATCH)
    // @ResponseStatus -> Fija el código HTTP de respuesta

@Tag(name = "Libros", description = "Gestión de libros")
@RequestMapping("api/libros")
@RestController
public class LibroRestController {
    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    /** Servicio de libros para la lógica de negocio */
    private final LibroService libroService;
    /** Servicio de categorías para validar relaciones */
    private final CategoriaService categoriaService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param libroService Servicio de libros
     * @param categoriaService Servicio de categorías
     */
    public LibroRestController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    ///////////////////////////////////////////////////////////////
    /// ANOTACIONES
    ///////////////////////////////////////////////////////////////

    // ================== ENDPOINTS LIBROS ==================
        // Ruta base: /api/libros

        // GET    /api/libros                -> Lista TODOS los libros
        // GET    /api/libros/disponibles    -> Lista libros DISPONIBLES
        // GET    /api/libros/autor/{autor}  -> Lista libros de un AUTOR      | @PathVariable autor
        // GET    /api/libros/{id}/libros    -> Lista libros de una CATEGORÍA | @PathVariable id (categoría)
        // GET    /api/libros/{id}           -> Obtiene UN libro por id       | @PathVariable id
        // POST   /api/libros                -> CREA un libro                 | @RequestBody LibroPeticionDto
        // PUT    /api/libros/{id}           -> MODIFICA un libro por id      | @PathVariable + @RequestBody
        // DELETE /api/libros/{id}           -> BORRA un libro por id         | @PathVariable id

        // Respuestas: 200 -> OK | 404 -> No encontrado


    //----GET PARA OBTENER TODOS LOS LIBROS DISPONIBLES-----
    @Operation(summary = "Listar libros disponibles")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/disponibles")
    public List<Libro> listarLibrosDisponibles(){
        return libroService.listarLibrosDisponibles();
    }


    /**
     * Endpoint para listar todos los libros del sistema.
     * GET /api/libros
     *
     * @return Lista completa de libros
     */
    //----GET PARA OBTENER TODOS LOS LIBROS-------
    @Operation(summary = "Listar todos los libros")
    @ApiResponse(responseCode = "200", description = "Mostrando todos los libros de la colección")
    @ApiResponse(responseCode = "404", description = "No hay libros en esta colección")
    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }


    /**
     * Endpoint para listar libros de un autor específico.
     * GET /api/libros/autor/{autor}
     *
     * @param autor Nombre del autor a buscar
     * @return Lista de libros del autor
     */
    //----GET PARA OBTENER TODOS LIBROS POR AUTOR----
    @Operation(summary = "Obtener libros por autor")
    @ApiResponse(responseCode = "200", description = "Este autor ha escrito estos libros")
    @ApiResponse(responseCode = "404", description = "Este autor es un fantasma y/o no existe")
    @GetMapping("autor/{autor}")
    public List<Libro> getLibroByAutor(@PathVariable String autor) {
        return libroService.listarLibrosDeAutor(autor);
    }

    /**
     * Endpoint para listar libros de una categoría específica.
     * GET /api/libros/{id}/libros
     *
     * @param id Identificador de la categoría
     * @return Lista de libros de la categoría
     */
    //----GET PARA OBTENER TODOS LIBROS POR CATEGORIA-----
    @Operation(summary = "Obtener libros por categoría")
    @ApiResponse(responseCode = "200", description = "Hay libros en esta categoría")
    @ApiResponse(responseCode = "404", description = "Esta categoría no contiene libros")
    @GetMapping("{id}/libros")
    public List<Libro> listarLibrosCategoria(@PathVariable Long id){
        categoriaService.buscarPorId(id);
        return libroService.obtenerLibrosDeCategoria(id);
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
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
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
    @ApiResponse(responseCode = "200", description = "Libro añadido exitosamente")
    @ApiResponse(responseCode = "404", description = "Hubo un fallo al añadir este libro")
    @PostMapping
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
    @ApiResponse(responseCode = "404", description = "Libro no modificada, posiblemente se deba a que no existe")
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
    @ApiResponse(responseCode = "200", description = "Libro encontrado y eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado, y por ende, no eliminado")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        libroService.eliminar(id);
    }

}
