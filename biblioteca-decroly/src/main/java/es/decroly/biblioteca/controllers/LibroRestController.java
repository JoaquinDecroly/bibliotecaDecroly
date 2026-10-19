package es.decroly.biblioteca.controllers;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.services.LibroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Tag(name = "Libros", description = "Gestión de libros")
@RequestMapping("api/libros")
@RestController
public class LibroRestController {
    private final List<Libro> libro = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    private final LibroService libroService;

    public LibroRestController(LibroService libroService) {
        this.libroService = libroService;
    }


}
