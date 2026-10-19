package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarCategorias();

    Categoria buscarPorId(Long id);

    Categoria crear(Categoria categoria);

    Categoria modificar(Long id, Categoria categoria);

    void eliminar(Long id);
}
