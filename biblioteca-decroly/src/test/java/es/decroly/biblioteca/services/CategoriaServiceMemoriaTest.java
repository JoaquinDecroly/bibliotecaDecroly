package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoriaServiceMemoriaTest {
    private final CategoriaServiceMemoria servicio = new CategoriaServiceMemoria();

    @Test
    void crearAsignaIdYPermiteBuscarCategoria() {
        Categoria creada = servicio.crear(categoriaDto("Novela", "Ficción"));

        assertEquals(1L, creada.getId());
        assertEquals("Novela", servicio.buscarPorId(creada.getId()).getNombre());
        assertEquals(1, servicio.listarCategorias().size());
    }

    @Test
    void crearRechazaNombreVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(categoriaDto(" ", null)));
    }

    @Test
    void crearRechazaNombreNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(categoriaDto(null, null)));
    }

    @Test
    void crearRechazaCategoriaNula() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(null));
    }

    @Test
    void modificarMantieneId() {
        Categoria primera = servicio.crear(categoriaDto("Novela", null));
        Categoria segunda = servicio.crear(categoriaDto("Poesía", null));

        Categoria actualizada = servicio.modificar(
                primera.getId(), categoriaDto("Histórica", "Subgénero"));

        assertEquals(primera.getId(), actualizada.getId());
        assertEquals("Histórica", servicio.buscarPorId(primera.getId()).getNombre());
    }

    @Test
    void modificarRechazaCategoriaNula() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, null));
    }

    @Test
    void modificarRechazaNombreVacio() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, categoriaDto(" ", null)));
    }

    @Test
    void modificarRechazaNombreNulo() {
        assertThrows(BadRequestException.class,
                () -> servicio.modificar(1L, categoriaDto(null, null)));
    }

    @Test
    void buscarYEliminarCategoriaInexistenteDevuelvenNotFound() {
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(99L));
        assertThrows(NotFoundException.class, () -> servicio.eliminar(99L));
    }

    @Test
    void eliminarQuitaLaCategoria() {
        Categoria creada = servicio.crear(categoriaDto("Novela", null));

        servicio.eliminar(creada.getId());

        assertEquals(0, servicio.listarCategorias().size());
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(creada.getId()));
    }

    private CategoriaPeticionDto categoriaDto(String nombre, String descripcion) {
        return new CategoriaPeticionDto(nombre, descripcion);
    }
}
