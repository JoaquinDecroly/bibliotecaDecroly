package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

@Service
public class LibroServiceMemoria implements LibroService {
    private static final Pattern ISBN_PATTERN = Pattern.compile("[0-9]{13}");

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    @Override
    public synchronized List<Libro> listarProductos() {
        return libros.stream().map(this::copiar).toList();
    }

    @Override
    public synchronized Libro buscarPorId(Long id) {
        return copiar(buscar(id));
    }

    @Override
    public synchronized Libro crear(Libro libro) {
        validar(libro);
        if (libros.stream().anyMatch(existente -> existente.getIsbn().equals(libro.getIsbn()))) {
            throw new BadRequestException("Ya existe un libro con ese ISBN");
        }

        Libro nuevo = copiar(libro);
        nuevo.setId(secuencia.incrementAndGet());
        nuevo.setDisponibles(nuevo.getEjemplares());
        libros.add(nuevo);
        return copiar(nuevo);
    }

    @Override
    public synchronized Libro modificar(Long id, Libro libro) {
        Libro existente = buscar(id);
        validar(libro);
        if (libros.stream().anyMatch(otro ->
                !otro.getId().equals(id) && otro.getIsbn().equals(libro.getIsbn()))) {
            throw new BadRequestException("Ya existe un libro con ese ISBN");
        }

        Libro actualizado = copiar(libro);
        actualizado.setId(id);
        actualizado.setDisponibles(existente.getDisponibles());
        libros.set(libros.indexOf(existente), actualizado);
        return copiar(actualizado);
    }

    @Override
    public synchronized void eliminar(Long id) {
        libros.remove(buscar(id));
    }

    private void validar(Libro libro) {
        if (libro == null) {
            throw new BadRequestException("El libro es obligatorio");
        }
        if (libro.getIsbn() == null || !ISBN_PATTERN.matcher(libro.getIsbn()).matches()) {
            throw new BadRequestException("El ISBN debe tener exactamente 13 cifras");
        }
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new BadRequestException("El título es obligatorio");
        }
        if (libro.getAutor() == null || libro.getAutor().isBlank()) {
            throw new BadRequestException("El autor es obligatorio");
        }
        if (libro.getEjemplares() < 1) {
            throw new BadRequestException("Debe haber al menos un ejemplar");
        }
    }

    private Libro buscar(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe el libro con id " + id));
    }

    private Libro copiar(Libro libro) {
        return new Libro(
                libro.getId(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getEjemplares(),
                libro.getDisponibles(),
                libro.getCategoriaId()
        );
    }
}
