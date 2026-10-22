package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.exceptions.NotFoundException;
import es.decroly.biblioteca.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    //Listar todos las categorias
    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Esta es la colección de categorías")
    @ApiResponse(responseCode = "404", description = "No hay colección de categorías")
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    //Obtener una categoria por id
    @Operation(summary = "Obtener una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    //Añade una categoria
    @Operation(summary = "Añadir una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría añadida exitosamente")
    @ApiResponse(responseCode = "404", description = "Hubo un fallo al añadir la categoría")
    @PostMapping
    public Categoria add(@RequestBody Categoria c) {
        return categoriaService.crear(new Categoria(c.getNombre(), c.getDescripcion()));
    }

    //Modificar una categoria por id
    @Operation(summary = "Modificar una categoría por id")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada y modificada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada, y por ende, no modificada")
    @PutMapping("/{id}")
    public Categoria modProd(@PathVariable Long id, @RequestBody Categoria c) {
        return categoriaService.modificar(id, c);
    }

    //Eliminar una categoria por id
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

    //Función añadir una categoria
    public void add(String nombre, String descripcion) {
        Long nuevoId = secuencia.incrementAndGet();
        categorias.add(new Categoria(nuevoId, nombre, descripcion));
    }
}
