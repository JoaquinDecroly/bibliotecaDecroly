package es.decroly.biblioteca.controllers;


import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.services.CategoriaService;
import es.decroly.biblioteca.services.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Tag(name = "Libros", description = "Gestión de libros")
@RequestMapping("api/libros")
@RestController
public class LibroRestController {
    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroRestController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    ///////////////////////////////////////////////////////////////
    /// ANOTACIONES
    ///////////////////////////////////////////////////////////////

    //Listar libros dispponibles
    @Operation(summary = "Listar libros disponibles")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/disponibles")
    public List<Libro> listarLibrosDisponibles(){
        return libroService.listarLibrosDisponibles();
    }


    //Listar todos los libros
    @Operation(summary = "Listar todos los libros")
    @ApiResponse(responseCode = "200", description = "Mostrando todos los libros de la colección")
    @ApiResponse(responseCode = "404", description = "No hay libros en esta colección")
    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }


    @Operation(summary = "Obtener libros por autor")
    @ApiResponse(responseCode = "200", description = "Este autor ha escrito estos libros")
    @ApiResponse(responseCode = "404", description = "Este autor es un fantasma y/o no existe")
    @GetMapping("autor/{autor}")
    public List<Libro> getLibroByAutor(@PathVariable String autor) {
        return libroService.listarLibrosDeAutor(autor);
    }

    @Operation(summary = "Obtener libros por categoría")
    @ApiResponse(responseCode = "200", description = "Hay libros en esta categoría")
    @ApiResponse(responseCode = "404", description = "Esta categoría no contiene libros")
    @GetMapping("{id}/libros")
    public List<Libro> listarLibrosCategoria(@PathVariable Long id){
        categoriaService.buscarPorId(id);
        return libroService.obtenerLibrosDeCategoria(id);
    }


    //Obtener un libro por id
    @Operation(summary = "Obtener un Libro por id")
    @ApiResponse(responseCode = "200", description = "Libro encontrado")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    //Añade un libro
    @Operation(summary = "Añadir un libro")
    @ApiResponse(responseCode = "200", description = "Libro añadido exitosamente")
    @ApiResponse(responseCode = "404", description = "Hubo un fallo al añadir este libro")
    @PostMapping
    public Libro add(@RequestBody LibroPeticionDto dto) {
        return libroService.crear(dto);
    }


    //Modificar un libro por id
    @Operation(summary = "Modificar un Libro por id")
    @ApiResponse(responseCode = "200", description = "Libro encontrado y modificado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no modificada, posiblemente se deba a que no existe")
    @PutMapping("/{id}")
    public Libro modProd(@PathVariable Long id, @RequestBody LibroPeticionDto dto) {
        return libroService.modificar(id, dto);
    }


    //Eliminar un libro por id
    @Operation(summary = "Borrar un Libro por id")
    @ApiResponse(responseCode = "200", description = "Libro encontrado y eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Libro no encontrado, y por ende, no eliminado")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        libroService.eliminar(id);
    }

}
