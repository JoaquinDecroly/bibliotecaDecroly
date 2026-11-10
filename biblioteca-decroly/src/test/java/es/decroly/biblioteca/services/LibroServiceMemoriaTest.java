package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.ConflictoException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import es.decroly.biblioteca.services.categoria.CategoriaServiceMemoria;
import es.decroly.biblioteca.services.libro.LibroServiceMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibroServiceMemoriaTest {
    /** Número de libros de prueba que carga LibroServiceMemoria */
    private static final int LIBROS_INICIALES = 40;
    /** ISBN que no tiene ningún libro de prueba */
    private static final String ISBN_LIBRE = "9781234567890";
    /** ISBN del libro de prueba con id 2 */
    private static final String ISBN_LIBRO_2 = "9780156013925";

    private final LibroServiceMemoria servicio = new LibroServiceMemoria(new CategoriaServiceMemoria());

    @Test
    void crearInicializaDisponiblesConEjemplares() {
        Libro creado = servicio.crear(libroDto(ISBN_LIBRE, "Título", "Autor", 2020, 3));

        assertEquals(LIBROS_INICIALES + 1L, creado.getId());
        assertEquals(3, creado.getEjemplares());
        assertEquals(3, creado.getDisponibles());
        assertEquals(3, servicio.buscarPorId(creado.getId()).getDisponibles());
    }

    @Test
    void crearRechazaIsbnVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("", "Título", "Autor", 2020, 1)));
    }

    @Test
    void crearRechazaIsbnNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(null, "Título", "Autor", 2020, 1)));
    }

    @Test
    void crearRechazaIsbnQueNoTiene13Cifras() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("123456789012", "Título", "Autor", 2020, 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("12345678901234", "Título", "Autor", 2020, 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto("97812345678AB", "Título", "Autor", 2020, 1)));
    }

    @Test
    void crearRechazaIsbnRepetido() {
        assertThrows(ConflictoException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRO_2, "Título", "Autor", 2020, 1)));
    }

    @Test
    void crearRechazaDatosObligatoriosVaciosOEjemplaresNoPositivos() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRE, " ", "Autor", 2020, 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRE, "Título", "", 2020, 1)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRE, "Título", "Autor", 2020, 0)));
    }

    @Test
    void crearRechazaLibroNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(null));
    }

    @Test
    void crearRechazaTituloNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRE, null, "Autor", 2020, 1)));
    }

    @Test
    void crearRechazaAutorNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(libroDto(ISBN_LIBRE, "Título", null, 2020, 1)));
    }

    @Test
    void crearRechazaCategoriaNulaOInexistente() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(new LibroPeticionDto(ISBN_LIBRE, "Título", "Autor", 2020, 1, null)));
        assertThrows(BadRequestException.class,
                () -> servicio.crear(new LibroPeticionDto(ISBN_LIBRE, "Título", "Autor", 2020, 1, 99L)));
    }

    @Test
    void modificarActualizaEjemplaresYDisponibles() {
        Libro modificado = servicio.modificar(1L, libroDto(ISBN_LIBRE, "Título", "Autor", 2020, 5));

        assertEquals(5, modificado.getEjemplares());
        assertEquals(5, modificado.getDisponibles());
    }

    @Test
    void modificarPermiteMantenerElMismoIsbn() {
        Libro modificado = servicio.modificar(2L, libroDto(ISBN_LIBRO_2, "Otro título", "Autor", 2020, 1));

        assertEquals("Otro título", modificado.getTitulo());
    }

    @Test
    void modificarRechazaIsbnDeOtroLibro() {
        assertThrows(ConflictoException.class,
                () -> servicio.modificar(1L, libroDto(ISBN_LIBRO_2, "Título", "Autor", 2020, 1)));
    }

    @Test
    void modificarLibroInexistenteDevuelveNotFound() {
        assertThrows(NotFoundException.class,
                () -> servicio.modificar(99L, libroDto(ISBN_LIBRE, "Título", "Autor", 2020, 1)));
    }

    @Test
    void modificarRechazaLibroNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, null));
    }

    @Test
    void modificarRechazaIsbnVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto("", "Título", "Autor", 2020, 1)));
    }

    @Test
    void modificarRechazaTituloVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto(ISBN_LIBRE, " ", "Autor", 2020, 1)));
    }

    @Test
    void modificarRechazaEjemplaresCero() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, libroDto(ISBN_LIBRE, "Título", "Autor", 2020, 0)));
    }

    @Test
    void listarLibrosDeAutorFiltraPorContenidoSinDistinguirMayusculas() {
        assertEquals(1, servicio.listarLibrosDeAutor("TOLKIEN").size());
        assertEquals("El Hobbit", servicio.listarLibrosDeAutor("tolkien").get(0).getTitulo());
        assertEquals(2, servicio.listarLibrosDeAutor("king").size());
        assertTrue(servicio.listarLibrosDeAutor("no existe este autor").isEmpty());
    }

    @Test
    void listarLibrosDeAutorSinAutorDevuelveTodos() {
        assertEquals(LIBROS_INICIALES, servicio.listarLibrosDeAutor(null).size());
        assertEquals(LIBROS_INICIALES, servicio.listarLibrosDeAutor(" ").size());
    }

    @Test
    void obtenerLibrosDeCategoriaDevuelveSusLibros() {
        assertEquals(2, servicio.obtenerLibrosDeCategoria(23L).size());
        assertTrue(servicio.obtenerLibrosDeCategoria(1L).isEmpty());
    }

    @Test
    void obtenerLibrosDeCategoriaInexistenteDevuelveNotFound() {
        assertThrows(NotFoundException.class,
                () -> servicio.obtenerLibrosDeCategoria(99L));
    }

    @Test
    void buscarYEliminarLibroInexistenteDevuelvenNotFound() {
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(99L));
        assertThrows(NotFoundException.class, () -> servicio.eliminar(99L));
    }

    private LibroPeticionDto libroDto(String isbn, String titulo, String autor, Integer anio, int ejemplares) {
        return new LibroPeticionDto(isbn, titulo, autor, anio, ejemplares, 1L);
    }
}
