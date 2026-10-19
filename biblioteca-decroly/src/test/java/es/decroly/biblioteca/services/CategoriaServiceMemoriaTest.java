package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoriaServiceMemoriaTest {
    private final CategoriaServiceMemoria servicio = new CategoriaServiceMemoria();

    @Test
    void crearAsignaIdYPermiteBuscarCategoria() {
        Categoria creada = servicio.crear(new Categoria(null, "Novela", "Ficción"));

        assertEquals(1L, creada.getId());
        assertEquals("Novela", servicio.buscarPorId(creada.getId()).getNombre());
        assertEquals(1, servicio.listarCategorias().size());
    }

    @Test
    void crearRechazaNombreVacioODuplicado() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(new Categoria(null, " ", null)));
        servicio.crear(new Categoria(null, "Novela", null));

        assertThrows(BadRequestException.class,
                () -> servicio.crear(new Categoria(null, " novela ", null)));
    }

    @Test
    void modificarMantieneIdYRechazaNombreUsadoPorOtraCategoria() {
        Categoria primera = servicio.crear(new Categoria(null, "Novela", null));
        Categoria segunda = servicio.crear(new Categoria(null, "Poesía", null));

        Categoria actualizada = servicio.modificar(
                primera.getId(), new Categoria(null, "Histórica", "Subgénero"));

        assertEquals(primera.getId(), actualizada.getId());
        assertEquals("Histórica", servicio.buscarPorId(primera.getId()).getNombre());
        assertThrows(BadRequestException.class, () -> servicio.modificar(
                segunda.getId(), new Categoria(null, "Histórica", null)));
    }

    @Test
    void buscarYEliminarCategoriaInexistenteDevuelvenNotFound() {
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(99L));
        assertThrows(NotFoundException.class, () -> servicio.eliminar(99L));
    }

    @Test
    void eliminarQuitaLaCategoria() {
        Categoria creada = servicio.crear(new Categoria(null, "Novela", null));

        servicio.eliminar(creada.getId());

        assertEquals(0, servicio.listarCategorias().size());
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(creada.getId()));
    }
}
