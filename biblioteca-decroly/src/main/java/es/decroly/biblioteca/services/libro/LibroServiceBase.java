package es.decroly.biblioteca.services.libro;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.ConflictoException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import es.decroly.biblioteca.services.categoria.CategoriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Clase base que implementa la lógica común para el servicio de libros.
 * Proporciona una implementación en memoria de las operaciones CRUD y consultas especializadas.
 * Las implementaciones específicas (Memoria, JSON) heredan de esta clase.
 */
public class LibroServiceBase implements LibroService {
    /** Formato del ISBN: exactamente 13 cifras */
    private static final String FORMATO_ISBN = "[0-9]{13}";

    /** Lista de libros almacenados en memoria */
    private final List<Libro> libros;
    /** Generador de IDs secuenciales para nuevos libros */
    private final AtomicLong secuencia;
    /** Servicio de categorías para comprobar que la categoría del libro existe */
    private final CategoriaService categoriaService;

    /**
     * Constructor que inicializa el servicio con una lista de libros.
     * Calcula el ID máximo existente para continuar la secuencia.
     *
     * @param librosInd Lista inicial de libros
     * @param categoriaService Servicio de categorías
     */
    public LibroServiceBase(List<Libro> librosInd, CategoriaService categoriaService) {
        this.libros = new ArrayList<>(librosInd);
        this.categoriaService = categoriaService;

        long maxId = 0;

        for(Libro l : librosInd){
            if(l.getId() > maxId){
                maxId = l.getId();
            }
        }

        this.secuencia = new AtomicLong(maxId);
    }

    /**
     * Lista todos los libros del sistema.
     *
     * @return Lista completa de libros
     */
    //----METODO LISTAR LIBROS-----
    @Override
    public List<Libro> listarLibros() {
        return libros;
    }

    /**
     * Busca un libro por su identificador.
     *
     * @param id Identificador del libro
     * @return Libro encontrado
     * @throws NotFoundException si no existe un libro con ese ID
     */
    //----METODO BUSCAR LIBRO POR ID----
    @Override
    public Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el libro con id: " + id));
    }

    /**
     * Lista los libros que tienen ejemplares disponibles para préstamo.
     *
     * @return Lista de libros disponibles
     */
    //------METODO OBTENER LIBROS DISPONIBLES------
    @Override
    public List<Libro> listarLibrosDisponibles() {
        List<Libro> librosDisponibles = new ArrayList<>();

        listarLibros().forEach(l -> {
            if(l.getDisponibles() > 0){
                librosDisponibles.add(l);
            }
        });
        return librosDisponibles;
    }

    /**
     * Obtiene los libros que pertenecen a una categoría específica.
     *
     * @param id Identificador de la categoría
     * @return Lista de libros de la categoría
     * @throws NotFoundException si no existe la categoría
     */
    //-----METODO OBTNER LIBROS DE UNA CATEGORIA (DADO EL ID)----
    @Override
    public List<Libro> obtenerLibrosDeCategoria(Long id) {
        categoriaService.buscarPorId(id);
        List<Libro> librosDeCategoria = new ArrayList<>();

        listarLibros().forEach(l -> {
            if(id.equals(l.getCategoriaId())){
                librosDeCategoria.add(l);
            }
        });
        return librosDeCategoria;
    }

    /**
     * Lista los libros cuyo autor contiene el texto indicado.
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     * Si no se indica autor, devuelve todos los libros.
     *
     * @param autor Texto a buscar en el nombre del autor (puede ser null)
     * @return Lista de libros del autor
     */
    //-----METODO OBTENER LIBROS DE UN AUTOR (DADO EL NOMBRE DEL AUTOR)-----
    @Override
    public List<Libro> listarLibrosDeAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            return listarLibros();
        }
        List<Libro> librosDeAutor = new ArrayList<>();

        listarLibros().forEach(l -> {
            if(l.getAutor().toLowerCase().contains(autor.trim().toLowerCase())){
                librosDeAutor.add(l);
            }
        });
        return librosDeAutor;
    }

    /**
     * Crea un nuevo libro en el sistema.
     * Al crear, los disponibles son iguales a los ejemplares.
     *
     * @param libro DTO con los datos del libro a crear
     * @return Libro creado con su ID asignado
     * @throws BadRequestException si los datos son inválidos o la categoría no existe
     * @throws ConflictoException si ya existe un libro con ese ISBN
     */
    //-----METODO CREAR LIBRO-----
    @Override
    public Libro crear(LibroPeticionDto libro) {
        validar(libro);
        validarIsbnUnico(libro.getIsbn(), null);
        Long nId = secuencia.incrementAndGet();
        Libro l = new Libro(nId, libro.getIsbn(), libro.getTitulo(), libro.getAutor(),
                libro.getAnioPublicacion(), libro.getEjemplares(), libro.getEjemplares(), libro.getCategoriaId());
        libros.add(l);
        return l;
    }

    /**
     * Modifica un libro existente.
     * Los disponibles se actualizan según el cambio en los ejemplares:
     * se mantienen los que ya están prestados (ejemplares - disponibles).
     *
     * @param id Identificador del libro a modificar
     * @param libro DTO con los nuevos datos del libro
     * @return Libro modificado
     * @throws NotFoundException si no existe el libro
     * @throws BadRequestException si los datos son inválidos o la categoría no existe
     * @throws ConflictoException si otro libro ya tiene ese ISBN
     */
    //----METODO MODIFICAR LIBRO----
    @Override
    public Libro modificar(Long id, LibroPeticionDto libro) {
        validar(libro);
        Libro libroMod = buscarPorId(id);
        validarIsbnUnico(libro.getIsbn(), id);

        int prestados = libroMod.getEjemplares() - libroMod.getDisponibles();
        if (libro.getEjemplares() < prestados) {
            throw new BadRequestException("No puede haber menos ejemplares que libros prestados (" + prestados + ")");
        }

        libroMod.setIsbn(libro.getIsbn());
        libroMod.setTitulo(libro.getTitulo());
        libroMod.setAutor(libro.getAutor());
        libroMod.setAnioPublicacion(libro.getAnioPublicacion());
        libroMod.setEjemplares(libro.getEjemplares());
        libroMod.setDisponibles(libro.getEjemplares() - prestados);
        libroMod.setCategoriaId(libro.getCategoriaId());

        return libroMod;
    }

    /**
     * Elimina un libro del sistema.
     *
     * @param id Identificador del libro a eliminar
     * @throws NotFoundException si no existe el libro
     */
    //------METODO ELIMINAR LIBRO-----
    @Override
    public void eliminar(Long id) {
        boolean remove = libros.removeIf(l -> l.getId().equals(id));
        if (!remove) {
            throw new NotFoundException("No se encontró el libro con id: " + id);
        }
    }

    ////////////////////////////////////////////
    /**
     * Valida los datos de un libro antes de crear o modificar.
     *
     * @param dto DTO con los datos a validar
     * @throws BadRequestException si los datos son inválidos o la categoría no existe
     */
    //----AUXILIAR METODO VALIDACION----
    private void validar(LibroPeticionDto dto) {
        if (dto == null) {
            throw new BadRequestException("El libro es obligatorio");
        }
        if (dto.getIsbn() == null || dto.getIsbn().isBlank()) {
            throw new BadRequestException("El ISBN es obligatorio");
        }
        if (!dto.getIsbn().matches(FORMATO_ISBN)) {
            throw new BadRequestException("El ISBN debe tener exactamente 13 cifras");
        }
        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new BadRequestException("El título es obligatorio");
        }
        if (dto.getAutor() == null || dto.getAutor().isBlank()) {
            throw new BadRequestException("El autor es obligatorio");
        }
        if (dto.getEjemplares() < 1) {
            throw new BadRequestException("Debe haber al menos un ejemplar");
        }
        if (dto.getCategoriaId() == null) {
            throw new BadRequestException("La categoría es obligatoria");
        }
        if (!categoriaService.existePorId(dto.getCategoriaId())) {
            throw new BadRequestException("No existe la categoría con id: " + dto.getCategoriaId());
        }
    }

    /**
     * Comprueba que no haya otro libro con el mismo ISBN.
     *
     * @param isbn ISBN a comprobar
     * @param idIgnorado Id del libro que se está modificando (null al crear)
     * @throws ConflictoException si otro libro ya tiene ese ISBN
     */
    //----AUXILIAR METODO VALIDACION ISBN NO REPETIDO----
    private void validarIsbnUnico(String isbn, Long idIgnorado) {
        boolean isbnExiste = libros.stream()
                .anyMatch(l -> !l.getId().equals(idIgnorado) && l.getIsbn().equals(isbn));
        if (isbnExiste) {
            throw new ConflictoException("Ya existe un libro con el ISBN: " + isbn);
        }
    }
}
