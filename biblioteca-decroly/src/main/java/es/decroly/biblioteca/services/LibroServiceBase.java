package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;
import es.decroly.biblioteca.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LibroServiceBase implements LibroService {
    private final List<Libro> libros;
    private AtomicLong secuencia = new AtomicLong();

    public LibroServiceBase(List<Libro> librosInd) {
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

    @Override
    public List<Libro> listarProductos() {
        return libros;
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el libro con id: " + id));
    }

    @Override
    public Libro crear(Libro libro) {
        Long nId = secuencia.incrementAndGet();
        boolean add = libros.add(new Libro(nId, libro.getTitulo(), libro.getIsbn(), libro.getAutor(), libro.getAnioPublicacion()));

        System.out.println("Los ejemplares y disponibles se inicializaron a 1, y sin categoría\n");
        return add ? libro : null;
    }

    @Override
    public Libro modificar(Long id, Libro libro) {
        Libro libroMod = buscarPorId(id);
        libroMod.setId(libro.getId());
        libroMod.setIsbn(libro.getIsbn());
        libroMod.setTitulo(libro.getTitulo());
        libroMod.setAutor(libro.getAutor());
        libroMod.setAnioPublicacion(libro.getAnioPublicacion());
        libroMod.setEjemplares(libro.getEjemplares());
        libroMod.setDisponibles(libro.getDisponibles());
        libroMod.setCategoriaId(libro.getCategoriaId());

        return libroMod;
    }

    @Override
    public void eliminar(Long id) {
        boolean remove = libros.removeIf(l -> l.getId().equals(id));

        if (!remove) {
            throw new NotFoundException("No se encontró el producto con id: " + id);
        }
        throw new NotFoundException("Se encontró y se borró exitosamente el producto con id: " + id);
    }
}
