package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

@Service
public class LibroServiceMemoria extends LibroServiceBase {
//    public class LibroServiceMemoria implements LibroService extends LibroServiceBase
    public LibroServiceMemoria() {
        super(List.of(
                new Libro(1L, "Clean Code", "9780132350884", "Robert C. Martin", "2008"),
                new Libro(2L, "El Principito", "9780156013925", "Antoine de Saint-Exupéry", "1943"),
                new Libro(3L, "Sapiens", "9780062316097", "Yuval Noah Harari", "2011"),
                new Libro(4L, "Breve historia del tiempo", "9780553380163", "Stephen Hawking", "1988"),
                new Libro(5L, "Los pilares de la Tierra", "9780451237806", "Ken Follett", "1989"),
                new Libro(6L, "El Hobbit", "9780547928227", "J.R.R. Tolkien", "1937"),
                new Libro(7L, "El código Da Vinci", "9780307474278", "Dan Brown", "2003"),
                new Libro(8L, "Orgullo y Prejuicio", "9780141439518", "Jane Austen", "1813"),
                new Libro(9L, "El resplandor", "9780307743657", "Stephen King", "1977"),
                new Libro(10L, "La isla del tesoro", "9780141324841", "Robert Louis Stevenson", "1883"),
                new Libro(11L, "Steve Jobs", "9781451648539", "Walter Isaacson", "2011"),
                new Libro(12L, "El poder del ahora", "9781577314806", "Eckhart Tolle", "1997"),
                new Libro(13L, "Así habló Zaratustra", "9780140441185", "Friedrich Nietzsche", "1883"),
                new Libro(14L, "El hombre en busca de sentido", "9780807014295", "Viktor Frankl", "1946"),
                new Libro(15L, "Pedagogía del oprimido", "9780140254037", "Paulo Freire", "1970"),
                new Libro(16L, "Cien años de soledad", "9780060883287", "Gabriel García Márquez", "1967"),
                new Libro(17L, "Veinte poemas de amor", "9780140187651", "Pablo Neruda", "1924"),
                new Libro(18L, "Dune", "9780441172719", "Frank Herbert", "1965"),
                new Libro(19L, "El pequeño príncipe", "9780156013925", "Antoine de Saint-Exupéry", "1943"),
                new Libro(20L, "Los juegos del hambre", "9780439023528", "Suzanne Collins", "2008"),
                new Libro(21L, "El rico padre el padre pobre", "9781612680194", "Robert Kiyosaki", "1997"),
                new Libro(22L, "Influencia", "9780061241895", "Robert Cialdini", "1984"),
                new Libro(23L, "Design Patterns", "9780201633610", "Erich Gamma", "1994"),
                new Libro(24L, "Superinteligencia", "9780198739838", "Nick Bostrom", "2014"),
                new Libro(25L, "El diseño de cosas", "9780465067107", "Don Norman", "1988"),
                new Libro(26L, "La cocina de mamá", "9788498016880", "Karlos Arguiñano", "1995"),
                new Libro(27L, "En la ruta de Marco Polo", "9788498726752", "Javier Reverte", "2001"),
                new Libro(28L, "Cómo no morir", "9781250066554", "Michael Greger", "2015"),
                new Libro(29L, "Bounce", "9780061723759", "Matthew Syed", "2010"),
                new Libro(30L, "Las religiones del mundo", "9780131535060", "Huston Smith", "1958"),
                new Libro(31L, "El príncipe", "9780140449150", "Niccolò Machiavelli", "1532"),
                new Libro(32L, "El espíritu de las leyes", "9780140444285", "Montesquieu", "1748"),
                new Libro(33L, "Arquitectura moderna", "9780500203964", "William Curtis", "1982"),
                new Libro(34L, "Historia del arte", "9780500203964", "E.H. Gombrich", "1950"),
                new Libro(35L, "Teoría musical", "9780131893638", "Benjamin Horvit", "1960"),
                new Libro(36L, "Maus", "9780679729778", "Art Spiegelman", "1986"),
                new Libro(37L, "Ensayos", "9780140449043", "Michel de Montaigne", "1580"),
                new Libro(38L, "El silencio de los inocentes", "9780345354901", "Thomas Harris", "1988"),
                new Libro(39L, "Mitología griega", "9780316341516", "Edith Hamilton", "1942"),
                new Libro(40L, "La sexta extinción", "9780805092998", "Elizabeth Kolbert", "2014")
        ));
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
}
