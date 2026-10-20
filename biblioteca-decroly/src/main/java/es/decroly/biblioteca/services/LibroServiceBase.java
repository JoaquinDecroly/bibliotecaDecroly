package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Libro;

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
        return null;
    }

    @Override
    public Libro crear(Libro libro) {
        return null;
    }

    @Override
    public Libro modificar(Long id, Libro libro) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
