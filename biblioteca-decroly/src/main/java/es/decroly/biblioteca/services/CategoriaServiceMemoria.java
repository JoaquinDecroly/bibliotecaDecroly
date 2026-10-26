package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación del servicio de categorías que almacena los datos en memoria.
 * Inicializa con una colección de categorías de prueba predefinidas.
 * Anotada con @Service para que Spring la detecte e inyecte automáticamente.
 */
@Service
public class CategoriaServiceMemoria extends CategoriaServiceBase {
    /**
     * Constructor que inicializa el servicio con una lista de categorías de prueba.
     *
     * @param properties Propiedades de configuración de la biblioteca
     */
    //------AÑADIENDO UNOS CUANTOS LIBROS DE PRUEBA-----
    public CategoriaServiceMemoria(BibliotecaProperties properties) {
        super(List.of(
                new Categoria(1L, "Tecnología", "Categoría de libros de tecnología"),
                new Categoria(2L, "Ficción", "Categoría de libros de ficción"),
                new Categoria(3L, "No ficción", "Categoría de libros de no ficción"),
                new Categoria(4L, "Ciencia", "Categoría de libros de ciencia"),
                new Categoria(5L, "Historia", "Categoría de libros de historia"),
                new Categoria(6L, "Fantasía", "Categoría de libros de fantasía"),
                new Categoria(7L, "Misterio", "Categoría de libros de misterio"),
                new Categoria(8L, "Romance", "Categoría de libros románticos"),
                new Categoria(9L, "Terror", "Categoría de libros de terror"),
                new Categoria(10L, "Aventura", "Categoría de libros de aventuras"),
                new Categoria(11L, "Biografía", "Categoría de libros biográficos"),
                new Categoria(12L, "Autoayuda", "Categoría de libros de autoayuda"),
                new Categoria(13L, "Filosofía", "Categoría de libros de filosofía"),
                new Categoria(14L, "Psicología", "Categoría de libros de psicología"),
                new Categoria(15L, "Educación", "Categoría de libros educativos"),
                new Categoria(16L, "Literatura clásica", "Categoría de libros de literatura clásica"),
                new Categoria(17L, "Poesía", "Categoría de libros de poesía"),
                new Categoria(18L, "Ciencia ficción", "Categoría de libros de ciencia ficción"),
                new Categoria(19L, "Infantil", "Categoría de libros infantiles"),
                new Categoria(20L, "Juvenil", "Categoría de libros juveniles"),
                new Categoria(21L, "Economía", "Categoría de libros de economía y finanzas"),
                new Categoria(22L, "Marketing", "Categoría de libros de marketing y publicidad"),
                new Categoria(23L, "Programación", "Categoría de libros de programación y desarrollo de software"),
                new Categoria(24L, "Inteligencia artificial", "Categoría de libros sobre inteligencia artificial"),
                new Categoria(25L, "Diseño", "Categoría de libros de diseño gráfico y creatividad"),
                new Categoria(26L, "Cocina", "Categoría de libros de cocina y gastronomía"),
                new Categoria(27L, "Viajes", "Categoría de libros de viajes y turismo"),
                new Categoria(28L, "Salud", "Categoría de libros de salud y bienestar"),
                new Categoria(29L, "Deportes", "Categoría de libros de deportes y actividad física"),
                new Categoria(30L, "Religión", "Categoría de libros de religión y espiritualidad"),
                new Categoria(31L, "Política", "Categoría de libros de política y sociedad"),
                new Categoria(32L, "Derecho", "Categoría de libros de derecho y legislación"),
                new Categoria(33L, "Arquitectura", "Categoría de libros de arquitectura y urbanismo"),
                new Categoria(34L, "Arte", "Categoría de libros de arte e historia del arte"),
                new Categoria(35L, "Música", "Categoría de libros de música y teoría musical"),
                new Categoria(36L, "Cómics", "Categoría de cómics y novelas gráficas"),
                new Categoria(37L, "Ensayo", "Categoría de libros de ensayo"),
                new Categoria(38L, "Crimen", "Categoría de libros de crimen y novela negra"),
                new Categoria(39L, "Mitología", "Categoría de libros de mitología y leyendas"),
                new Categoria(40L, "Medio ambiente", "Categoría de libros sobre ecología y medio ambiente")
        ), properties);
    }

//    @Override
//    public List<Categoria> listarCategorias() {
//        return categorias.stream().map(this::copiar).toList();
//    }
//
//    @Override
//    public Categoria buscarPorId(Long id) {
//        return copiar(buscar(id));
//    }
//
//    @Override
//    public Categoria crear(Categoria categoria) {
//        validar(categoria);
//        validarNombreUnico(categoria.getNombre(), null);
//
//        Categoria nueva = copiar(categoria);
//        nueva.setId(secuencia.incrementAndGet());
//        categorias.add(nueva);
//        return copiar(nueva);
//    }
//
//    @Override
//    public Categoria modificar(Long id, Categoria categoria) {
//        Categoria existente = buscar(id);
//        validar(categoria);
//        validarNombreUnico(categoria.getNombre(), id);
//
//        Categoria actualizada = copiar(categoria);
//        actualizada.setId(id);
//        categorias.set(categorias.indexOf(existente), actualizada);
//        return copiar(actualizada);
//    }
//
//    @Override
//    public void eliminar(Long id) {
//        categorias.remove(buscar(id));
//    }
//
//    private void validar(Categoria categoria) {
//        if (categoria == null) {
//            throw new BadRequestException("La categoría es obligatoria");
//        }
//        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
//            throw new BadRequestException("El nombre de la categoría es obligatorio");
//        }
//    }
//
//    private void validarNombreUnico(String nombre, Long idIgnorado) {
//        boolean duplicado = categorias.stream().anyMatch(categoria ->
//                !categoria.getId().equals(idIgnorado)
//                        && categoria.getNombre().trim().equalsIgnoreCase(nombre.trim()));
//        if (duplicado) {
//            throw new BadRequestException("Ya existe una categoría con ese nombre");
//        }
//    }
//
//    private Categoria buscar(Long id) {
//        return categorias.stream()
//                .filter(categoria -> categoria.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("No existe la categoría con id " + id));
//    }
//
//    private Categoria copiar(Categoria categoria) {
//        return new Categoria(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
//    }
}
