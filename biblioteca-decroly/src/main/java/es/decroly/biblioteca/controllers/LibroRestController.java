package es.decroly.biblioteca.controllers;


import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.services.CategoriaService;
import es.decroly.biblioteca.services.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

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
    @GetMapping("/disponibles")
    public List<Libro> listarLibrosDisponibles(){
        return libroService.listarLibros().stream()
                .filter(libro -> libro.getDisponibles() > 0).toList();
    }


    //Listar todos los libros
    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
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
    @PostMapping
    public Libro add(@RequestBody Libro l) {
        return libroService.crear(new Libro(l.getIsbn(), l.getTitulo(), l.getAutor(), l.getAnioPublicacion(), l.getCategoriaId()));
    }

    //Modificar un libro por id
    @PutMapping("/{id}")
    public Libro modProd(@PathVariable Long id, @RequestBody Libro l) {
        return libroService.modificar(id, l);
    }

    //Eliminar un libro por id
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        libroService.eliminar(id);
    }

}
