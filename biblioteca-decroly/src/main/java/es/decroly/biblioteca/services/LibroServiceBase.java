package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LibroServiceBase implements LibroService {
    private final List<Libro> libros;
    private AtomicLong secuencia = new AtomicLong();

    public LibroServiceBase(List<Libro> librosInd, BibliotecaProperties properties) {
//        crear("teclado", 49.8, 15);
//        crear("raton", 66.3, 40);
//        crear("mando", 87.5, 17);
        this.libros = new ArrayList<>(librosInd);

        long maxId = 0;

        for(Libro l : librosInd){
            if(l.getId() > maxId){
                maxId = l.getId();
            }
        }

        this.secuencia = new AtomicLong(maxId);
    }

    //----METODO LISTAR LIBROS-----
    @Override
    public List<Libro> listarLibros() {
        return libros;
    }

    //----METODO BUSCAR LIBRO POR ID----
    @Override
    public Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el libro con id: " + id));
    }

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

    //-----METODO OBTNER LIBROS DE UNA CATEGORIA (DADO EL ID)----
    @Override
    public List<Libro> obtenerLibrosDeCategoria(Long id) {
        List<Libro> librosDeCategoria = new ArrayList<>();

        listarLibros().forEach(l -> {
            if(l.getCategoriaId().equals(id)){
                librosDeCategoria.add(l);
            }
        });
        return librosDeCategoria;
    }

    //-----METODO OBTENER LIBROS DE UN AUTOR (DADO EL NOMBRE DEL AUTOR)-----
    @Override
    public List<Libro> listarLibrosDeAutor(String autor) {
        List<Libro> librosDeAutor = new ArrayList<>();

        listarLibros().forEach(l -> {
            if(l.getAutor().equalsIgnoreCase(autor)){
                librosDeAutor.add(l);
            }
        });
        return librosDeAutor;
    }

    //-----METODO CREAR CATEGORIA-----
    @Override
    public Libro crear(LibroPeticionDto libro) {
        validar(libro);
        Long nId = secuencia.incrementAndGet();
        Libro l = new Libro(nId, libro.getIsbn(), libro.getTitulo(), libro.getAutor(),
                libro.getAnioPublicacion(), libro.getCategoriaId());
        libros.add(l);

        System.out.println("Los ejemplares y disponibles se inicializaron a 1, y sin categoría\n");
        return l;
    }

    //----METODO MODIFICAR CATEGORIA----
    @Override
    public Libro modificar(Long id, LibroPeticionDto libro) {
        validar(libro);
        Libro libroMod = buscarPorId(id);
        libroMod.setIsbn(libro.getIsbn());
        libroMod.setTitulo(libro.getTitulo());
        libroMod.setAutor(libro.getAutor());
        libroMod.setAnioPublicacion(libro.getAnioPublicacion());
        libroMod.setEjemplares(libro.getEjemplares());
        libroMod.setCategoriaId(libro.getCategoriaId());

        return libroMod;
    }

//    @Override
//    public Libro crear(Libro libro) {
//        Long nId = secuencia.incrementAndGet();
//        Libro l = new Libro(nId, libro.getIsbn(), libro.getTitulo(), libro.getAutor(),
//                libro.getAnioPublicacion(), libro.getCategoriaId());
//        libros.add(l);
//
//        System.out.println("Los ejemplares y disponibles se inicializaron a 1, y sin categoría\n");
//        return l;
//    }

//    //Función añadir un libro
//    public void add(String isbn, String titulo, String autor, String fechaPublicacion, String categoria) {
//        Long nuevoId = secuencia.incrementAndGet();
//
//        Long categoriaId = null;
//        AtomicReference<Long> n = new AtomicReference<>();
//
//
//        categoriaService.listarCategorias().forEach((c) -> {
//            if(c != null && c.getNombre().equals(categoria)){
//                n.set(c.getId());
//            }
//        });
//
//        categoriaId = n.get();
//        libros.add(new Libro(nuevoId, isbn, titulo, autor, fechaPublicacion, categoriaId));
//    }

//    @Override
//    public Libro modificar(Long id, Libro libro) {
//        Libro libroMod = buscarPorId(id);
//        libroMod.setId(libro.getId());
//        libroMod.setIsbn(libro.getIsbn());
//        libroMod.setTitulo(libro.getTitulo());
//        libroMod.setAutor(libro.getAutor());
//        libroMod.setAnioPublicacion(libro.getAnioPublicacion());
//        libroMod.setEjemplares(libro.getEjemplares());
//        libroMod.setDisponibles(libro.getDisponibles());
//        libroMod.setCategoriaId(libro.getCategoriaId());
//
//        return libroMod;
//    }

    @Override
    public void eliminar(Long id) {
        boolean remove = libros.removeIf(l -> l.getId().equals(id));
        if (!remove) {
            throw new NotFoundException("No se encontró el libro con id: " + id);
        }
    }

    private void validar(LibroPeticionDto dto) {
        if (dto == null) {
            throw new BadRequestException("El libro es obligatorio");
        }
        if (dto.getIsbn() == null || dto.getIsbn().isBlank()) {
            throw new BadRequestException("El ISBN es obligatorio");
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
    }
}
