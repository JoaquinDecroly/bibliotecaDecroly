package es.decroly.biblioteca.services.libro;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de libros que almacena los datos en memoria.
 * Inicializa con una colección de libros de prueba predefinidos.
 * Anotada con @Service para que Spring la detecte e inyecte automáticamente.
 */
@Service
public class LibroServiceMemoria extends LibroServiceBase {
    /**
     * Constructor que inicializa el servicio con una lista de libros de prueba.
     *
     * @param properties Propiedades de configuración de la biblioteca
     */
    //------AÑADIENDO UNOS CUANTOS LIBROS DE PRUEBA-----
public LibroServiceMemoria(BibliotecaProperties properties) {
    super(List.of(
            new Libro(1L, "9780132350884", "Clean Code", "Robert C. Martin", "2008", 23L),
            new Libro(2L, "9780156013925", "El Principito", "Antoine de Saint-Exupéry", "1943", 19L),
            new Libro(3L, "9780062316097", "Sapiens", "Yuval Noah Harari", "2011", 5L),
            new Libro(4L, "9780553380163", "Breve historia del tiempo", "Stephen Hawking", "1988", 4L),
            new Libro(5L, "9780451237806", "Los pilares de la Tierra", "Ken Follett", "1989", 16L),
            new Libro(6L, "9780547928227", "El Hobbit", "J.R.R. Tolkien", "1937", 6L),
            new Libro(7L, "9780307474278", "El código Da Vinci", "Dan Brown", "2003", 7L),
            new Libro(8L, "9780141439518", "Orgullo y Prejuicio", "Jane Austen", "1813", 8L),
            new Libro(9L, "9780307743657", "El resplandor", "Stephen King", "1977", 9L),
            new Libro(10L, "9780141324841", "La isla del tesoro", "Robert Louis Stevenson", "1883", 10L),
            new Libro(11L, "9781451648539", "Steve Jobs", "Walter Isaacson", "2011", 11L),
            new Libro(12L, "9781577314806", "El poder del ahora", "Eckhart Tolle", "1997", 12L),
            new Libro(13L, "9780140441185", "Así habló Zaratustra", "Friedrich Nietzsche", "1883", 13L),
            new Libro(14L, "9780807014295", "El hombre en busca de sentido", "Viktor Frankl", "1946", 14L),
            new Libro(15L, "9780140254037", "Pedagogía del oprimido", "Paulo Freire", "1970", 15L),
            new Libro(16L, "9780060883287", "Cien años de soledad", "Gabriel García Márquez", "1967", 16L),
            new Libro(17L, "9780140187651", "Veinte poemas de amor", "Pablo Neruda", "1924", 17L),
            new Libro(18L, "9780441172719", "Dune", "Frank Herbert", "1965", 18L),
            new Libro(19L, "9780156013925", "El pequeño príncipe", "Antoine de Saint-Exupéry", "1943", 19L),
            new Libro(20L, "9780439023528", "Los juegos del hambre", "Suzanne Collins", "2008", 20L),
            new Libro(21L, "9781612680194", "El rico padre el padre pobre", "Robert Kiyosaki", "1997", 21L),
            new Libro(22L, "9780061241895", "Influencia", "Robert Cialdini", "1984", 22L),
            new Libro(23L, "9780201633610", "Design Patterns", "Erich Gamma", "1994", 23L),
            new Libro(24L, "9780198739838", "Superinteligencia", "Nick Bostrom", "2014", 24L),
            new Libro(25L, "9780465067107", "El diseño de cosas", "Don Norman", "1988", 25L),
            new Libro(26L, "9788498016880", "La cocina de mamá", "Karlos Arguiñano", "1995", 26L),
            new Libro(27L, "9788498726752", "En la ruta de Marco Polo", "Javier Reverte", "2001", 27L),
            new Libro(28L, "9781250066554", "Cómo no morir", "Michael Greger", "2015", 28L),
            new Libro(29L, "9780061723759", "Bounce", "Matthew Syed", "2010", 29L),
            new Libro(30L, "9780131535060", "Las religiones del mundo", "Huston Smith", "1958", 30L),
            new Libro(31L, "9780140449150", "El príncipe", "Niccolò Machiavelli", "1532", 31L),
            new Libro(32L, "9780140444285", "El espíritu de las leyes", "Montesquieu", "1748", 31L),
            new Libro(33L, "9780500203964", "Arquitectura moderna", "William Curtis", "1982", 33L),
            new Libro(34L, "9780500203964", "Historia del arte", "E.H. Gombrich", "1950", 34L),
            new Libro(35L, "9780131893638", "Teoría musical", "Benjamin Horvit", "1960", 35L),
            new Libro(36L, "9780679729778", "Maus", "Art Spiegelman", "1986", 36L),
            new Libro(37L, "9780140449043", "Ensayos", "Michel de Montaigne", "1580", 37L),
            new Libro(38L, "9780345354901", "El silencio de los inocentes", "Thomas Harris", "1988", 38L),
            new Libro(39L, "9780316341516", "Mitología griega", "Edith Hamilton", "1942", 39L),
            new Libro(40L, "9780805092998", "La sexta extinción", "Elizabeth Kolbert", "2014", 40L)
    ), properties);
}
    //    private static final Pattern ISBN_PATTERN = Pattern.compile("[0-9]{13}");
//
//    private final List<Libro> libros = new ArrayList<>();
//    private final AtomicLong secuencia = new AtomicLong();
//
//    @Override
//    public synchronized List<Libro> listarProductos() {
//        return libros.stream().map(this::copiar).toList();
//    }
//
//    @Override
//    public synchronized Libro buscarPorId(Long id) {
//        return copiar(buscar(id));
//    }
//
//    @Override
//    public synchronized Libro crear(Libro libro) {
//        validar(libro);
//        if (libros.stream().anyMatch(existente -> existente.getIsbn().equals(libro.getIsbn()))) {
//            throw new BadRequestException("Ya existe un libro con ese ISBN");
//        }
//
//        Libro nuevo = copiar(libro);
//        nuevo.setId(secuencia.incrementAndGet());
//        nuevo.setDisponibles(nuevo.getEjemplares());
//        libros.add(nuevo);
//        return copiar(nuevo);
//    }
//
//    @Override
//    public synchronized Libro modificar(Long id, Libro libro) {
//        Libro existente = buscar(id);
//        validar(libro);
//        if (libros.stream().anyMatch(otro ->
//                !otro.getId().equals(id) && otro.getIsbn().equals(libro.getIsbn()))) {
//            throw new BadRequestException("Ya existe un libro con ese ISBN");
//        }
//
//        Libro actualizado = copiar(libro);
//        actualizado.setId(id);
//        actualizado.setDisponibles(existente.getDisponibles());
//        libros.set(libros.indexOf(existente), actualizado);
//        return copiar(actualizado);
//    }
//
//    @Override
//    public synchronized void eliminar(Long id) {
//        libros.remove(buscar(id));
//    }
//
//    private void validar(Libro libro) {
//        if (libro == null) {
//            throw new BadRequestException("El libro es obligatorio");
//        }
//        if (libro.getIsbn() == null || !ISBN_PATTERN.matcher(libro.getIsbn()).matches()) {
//            throw new BadRequestException("El ISBN debe tener exactamente 13 cifras");
//        }
//        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
//            throw new BadRequestException("El título es obligatorio");
//        }
//        if (libro.getAutor() == null || libro.getAutor().isBlank()) {
//            throw new BadRequestException("El autor es obligatorio");
//        }
//        if (libro.getEjemplares() < 1) {
//            throw new BadRequestException("Debe haber al menos un ejemplar");
//        }
//    }
//
//    private Libro buscar(Long id) {
//        return libros.stream()
//                .filter(libro -> libro.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("No existe el libro con id " + id));
//    }
//
//    private Libro copiar(Libro libro) {
//        return new Libro(
//                libro.getId(),
//                libro.getIsbn(),
//                libro.getTitulo(),
//                libro.getAutor(),
//                libro.getAnioPublicacion(),
//                libro.getEjemplares(),
//                libro.getDisponibles(),
//                libro.getCategoriaId()
//        );
//    }
    }
