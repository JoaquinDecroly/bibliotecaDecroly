package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CategoriaServiceBase implements CategoriaService{
    private final List<Categoria> categorias;
    private final AtomicLong secuencia;

    public CategoriaServiceBase(List<Categoria> categoriasInd, BibliotecaProperties properties){
        this.categorias = new ArrayList<>(categoriasInd);
        long maxId = 0;

        for(Categoria c : categoriasInd){
            if(c.getId() > maxId){
                maxId = c.getId();
            }
        }

        this.secuencia = new AtomicLong(maxId);
    }

    //-----METODO LISTAR CATEGORIAS----
    @Override
    public List<Categoria> listarCategorias() {
        return categorias;
    }

    //----METODO BUSCAR CATEGORIA POR ID------
    @Override
    public Categoria buscarPorId(Long id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró la categoría con id: " + id));
    }

    //-----METODO CREAR CATEGORIA-----
    @Override
    public Categoria crear(CategoriaPeticionDto categoria) {
        validar(categoria);
        Long nId = secuencia.incrementAndGet();
        Categoria nueva = new Categoria(nId, categoria.getNombre(), categoria.getDescripcion());
        categorias.add(nueva);
        return nueva;
    }

    //----METODO MODIFICAR CATEGORIA----
    @Override
    public Categoria modificar(Long id, CategoriaPeticionDto categoria) {
        validar(categoria);
        Categoria categoriaMod = buscarPorId(id);
        categoriaMod.setNombre(categoria.getNombre());
        categoriaMod.setDescripcion(categoria.getDescripcion());
        return categoriaMod;
    }

    //------METODO ELIMINAR CATEGORIA-----
    @Override
    public void eliminar(Long id) {
        boolean remove = categorias.removeIf(categoria -> categoria.getId().equals(id));
        if (!remove) {
            throw new NotFoundException("No se encontró la categoría con id: " + id);
        }
    }
    
////////////////////////////////////////////
    //----AUXILIAR METODO VALIDACION----
    private void validar(CategoriaPeticionDto dto) {
        if (dto == null) {
            throw new BadRequestException("La categoría es obligatoria");
        }
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new BadRequestException("El nombre es obligatorio");
        }
    }
}
