package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarCategorias();

    Categoria buscarPorId(Long id);

    Categoria crear(CategoriaPeticionDto categoria);

    Categoria modificar(Long id, CategoriaPeticionDto categoria);

    void eliminar(Long id);
}
