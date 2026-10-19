package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LibroServiceMemoriaTest {
    private final LibroServiceMemoria servicio = new LibroServiceMemoria();

    @Test
    void crearInicializaDisponiblesConEjemplares() {
        Libro creado = servicio.crear(libro("9781234567890", "Título", "Autor", 3));

        assertEquals(3, creado.getDisponibles());
        assertEquals(3, servicio.buscarPorId(creado.getId()).getDisponibles());
    }

    @Test
    void crearRechazaIsbnQueNoTieneTreceCifras() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libro("978abc", "Título", "Autor", 1)));
    }

    @Test
    void crearRechazaIsbnDuplicado() {
        servicio.crear(libro("9781234567890", "Título", "Autor", 1));

        assertThrows(BadRequestException.class,
                () -> servicio.crear(libro("9781234567890", "Otro título", "Otro autor", 1)));
    }

    @Test
    void crearRechazaDatosObligatoriosVaciosOEjemplaresNoPositivos() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libro("9781234567890", " ", "Autor", 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libro("9781234567890", "Título", "", 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libro("9781234567890", "Título", "Autor", 0)));
    }

    private Libro libro(String isbn, String titulo, String autor, int ejemplares) {
        return new Libro(null, isbn, titulo, autor, null, ejemplares, 0, null);
    }
}
