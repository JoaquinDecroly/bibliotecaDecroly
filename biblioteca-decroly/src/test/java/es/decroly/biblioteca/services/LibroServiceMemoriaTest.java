package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LibroServiceMemoriaTest {
    private final BibliotecaProperties properties = new BibliotecaProperties("Biblioteca Test", 3, 15);
    private final LibroServiceMemoria servicio = new LibroServiceMemoria(properties);

    @Test
    void crearInicializaDisponiblesConEjemplares() {
        Libro creado = servicio.crear(libroDto("9781234567890", "Título", "Autor", "2020", 3));

        assertEquals(3, creado.getDisponibles());
        assertEquals(3, servicio.buscarPorId(creado.getId()).getDisponibles());
    }

    @Test
    void crearRechazaIsbnVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("", "Título", "Autor", "2020", 1)));
    }

    @Test
    void crearRechazaIsbnNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(null, "Título", "Autor", "2020", 1)));
    }

    @Test
    void crearRechazaDatosObligatoriosVaciosOEjemplaresNoPositivos() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("9781234567890", " ", "Autor", "2020", 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("9781234567890", "Título", "", "2020", 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("9781234567890", "Título", "Autor", "2020", 0)));
    }

    @Test
    void crearRechazaLibroNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(null));
    }

    @Test
    void crearRechazaTituloNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("9781234567890", null, "Autor", "2020", 1)));
    }

    @Test
    void crearRechazaAutorNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("9781234567890", "Título", null, "2020", 1)));
    }

    @Test
    void modificarRechazaLibroNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, null));
    }

    @Test
    void modificarRechazaIsbnVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto("", "Título", "Autor", "2020", 1)));
    }

    @Test
    void modificarRechazaTituloVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto("9781234567890", " ", "Autor", "2020", 1)));
    }

    @Test
    void modificarRechazaEjemplaresCero() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto("9781234567890", "Título", "Autor", "2020", 0)));
    }

    private LibroPeticionDto libroDto(String isbn, String titulo, String autor, String anio, int ejemplares) {
        return new LibroPeticionDto(isbn, titulo, autor, anio, ejemplares, null);
    }
}
