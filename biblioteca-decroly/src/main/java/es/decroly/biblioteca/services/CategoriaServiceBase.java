package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.exceptions.NotFoundException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CategoriaServiceBase implements CategoriaService{
    private final List<Categoria> categorias;
    private final AtomicLong secuencia;

    public CategoriaServiceBase(List<Categoria> categoriasInd){
        this.categorias = categoriasInd;
        long maxId = 0;

        for(Categoria c : categoriasInd){
            if(c.getId() > maxId){
                maxId = c.getId();
            }
        }

        this.secuencia = new AtomicLong(maxId);
    }


    @Override
    public List<Categoria> listarCategorias() {
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró la categoría con id: " + id));
    }

    @Override
    public Categoria crear(Categoria categoria) {
        Long nId = secuencia.incrementAndGet();
        boolean add = categorias.add(new Categoria(nId, categoria.getNombre(), categoria.getDescripcion()));

        return add ? categoria : null;
    }

    @Override
    public Categoria modificar(Long id, Categoria categoria) {
        Categoria categoriaMod = buscarPorId(id);
        categoriaMod.setNombre(categoria.getNombre());
        categoriaMod.setDescripcion(categoria.getDescripcion());

        return categoriaMod;
    }

    @Override
    public void eliminar(Long id) {
        boolean remove = categorias.removeIf(categoria -> categoria.getId().equals(id));

        if (!remove) {
            throw new NotFoundException("No se encontró el producto con id: " + id);
        }
        throw new NotFoundException("Se encontró y se borró exitosamente el producto con id: " + id);
    }
}
