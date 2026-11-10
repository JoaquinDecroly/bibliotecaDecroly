package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.ConflictoException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import es.decroly.biblioteca.services.categoria.CategoriaServiceMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaServiceMemoriaTest {
    /** Número de categorías de prueba que carga CategoriaServiceMemoria */
    private static final int CATEGORIAS_INICIALES = 40;

    private final CategoriaServiceMemoria servicio = new CategoriaServiceMemoria();

    @Test
    void crearAsignaIdYPermiteBuscarCategoria() {
        Categoria creada = servicio.crear(categoriaDto("Novela", "Ficción"));

        assertEquals(CATEGORIAS_INICIALES + 1L, creada.getId());
        assertEquals("Novela", servicio.buscarPorId(creada.getId()).getNombre());
        assertEquals(CATEGORIAS_INICIALES + 1, servicio.listarCategorias().size());
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
    void crearRechazaNombreDeMasDe50Caracteres() {
        assertThrows(BadRequestException.class,
                () -> servicio.crear(categoriaDto("a".repeat(51), null)));
    }

    @Test
    void crearAceptaNombreDe50Caracteres() {
        Categoria creada = servicio.crear(categoriaDto("a".repeat(50), null));

        assertEquals(50, creada.getNombre().length());
    }

    @Test
    void crearRechazaNombreRepetidoSinDistinguirMayusculas() {
        assertThrows(ConflictoException.class,
                () -> servicio.crear(categoriaDto("TECNOLOGÍA", null)));
    }

    @Test
    void modificarMantieneId() {
        Categoria primera = servicio.crear(categoriaDto("Novela", null));

        Categoria actualizada = servicio.modificar(
                primera.getId(), categoriaDto("Histórica", "Subgénero"));

        assertEquals(primera.getId(), actualizada.getId());
        assertEquals("Histórica", servicio.buscarPorId(primera.getId()).getNombre());
    }

    @Test
    void modificarPermiteMantenerElMismoNombre() {
        Categoria actualizada = servicio.modificar(1L, categoriaDto("Tecnología", "Nueva descripción"));

        assertEquals("Nueva descripción", actualizada.getDescripcion());
    }

    @Test
    void modificarRechazaNombreDeOtraCategoria() {
        assertThrows(ConflictoException.class,
                () -> servicio.modificar(1L, categoriaDto("ficción", null)));
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
    void modificarCategoriaInexistenteDevuelveNotFound() {
        assertThrows(NotFoundException.class,
                () -> servicio.modificar(99L, categoriaDto("Novela", null)));
    }

    @Test
    void buscarYEliminarCategoriaInexistenteDevuelvenNotFound() {
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(99L));
        assertThrows(NotFoundException.class, () -> servicio.eliminar(99L));
    }

    @Test
    void existePorIdIndicaSiLaCategoriaExiste() {
        assertTrue(servicio.existePorId(1L));
        assertFalse(servicio.existePorId(99L));
    }

    @Test
    void eliminarQuitaLaCategoria() {
        Categoria creada = servicio.crear(categoriaDto("Novela", null));

        servicio.eliminar(creada.getId());

        assertEquals(CATEGORIAS_INICIALES, servicio.listarCategorias().size());
        assertThrows(NotFoundException.class, () -> servicio.buscarPorId(creada.getId()));
    }

    private CategoriaPeticionDto categoriaDto(String nombre, String descripcion) {
        return new CategoriaPeticionDto(nombre, descripcion);
    }
}
