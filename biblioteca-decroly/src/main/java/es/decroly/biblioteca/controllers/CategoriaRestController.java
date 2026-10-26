package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.exceptions.NotFoundException;
import es.decroly.biblioteca.services.CategoriaService;
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

@Tag(name = "Categorías", description = "Gestión de categorías")
@RequestMapping("api/categorias")
@RestController
public class CategoriaRestController {
    private final List<Categoria> categorias = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    private final CategoriaService categoriaService;

    public CategoriaRestController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    ///////////////////////////////////////////////////////////////
    /// ANOTACIONES
    ///////////////////////////////////////////////////////////////

    // ================== ENDPOINTS CATEGORÍAS ==================
        // Ruta base: /api/categorias

        // GET    /api/categorias        -> Lista TODAS las categorías
        // GET    /api/categorias/{id}   -> Obtiene UNA categoría por id   | @PathVariable id
        // POST   /api/categorias        -> CREA una categoría             | @RequestBody CategoriaPeticionDto
        // PUT    /api/categorias/{id}   -> MODIFICA una categoría por id  | @PathVariable + @RequestBody
        // DELETE /api/categorias/{id}   -> BORRA una categoría por id     | @PathVariable id

        // Respuestas: 200 -> OK | 404 -> No encontrado

////////////////////////////////////////////////////////////////////

    //----GET PARA OBTENER TODAS LAS CATEGORÍAS-----
    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Esta es la colección de categorías")
    @ApiResponse(responseCode = "404", description = "No hay colección de categorías")
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    //----GET PARA OBTENER CATEGORÍA POR ID----
    @Operation(summary = "Obtener una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    //-----POST PARA AÑADIR CATEGORIA-----
    @Operation(summary = "Añadir una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría añadida exitosamente")
    @ApiResponse(responseCode = "404", description = "Hubo un fallo al añadir la categoría")
    @PostMapping
    public Categoria add(@RequestBody CategoriaPeticionDto dto) {
        return categoriaService.crear(dto);
    }

    //----PUT PARA MODIFICAR CATEGORIA POR ID----
    @Operation(summary = "Modificar una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada y modificada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada, y por ende, no modificada")
    @PutMapping("/{id}")
    public Categoria modProd(@PathVariable Long id, @RequestBody CategoriaPeticionDto dto) {
        return categoriaService.modificar(id, dto);
    }

    //----DELETE PARA BORRAR CATEGORIA POR ID----
    @Operation(summary = "Borrar una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada y suprimida")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada, y por ende, no suprimida")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        categoriaService.eliminar(id);
    }

    ///////////////////////////////////////////////////////////////
    /// FUNCIONES AUXILIARES
    ///////////////////////////////////////////////////////////////

    //----AUX. PARA AÑADIR CATEGORIA-----
    public void add(String nombre, String descripcion) {
        Long nuevoId = secuencia.incrementAndGet();
        categorias.add(new Categoria(nuevoId, nombre, descripcion));
    }
}
