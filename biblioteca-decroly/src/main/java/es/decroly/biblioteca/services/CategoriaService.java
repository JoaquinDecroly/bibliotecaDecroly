package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;

import java.util.List;

public interface CategoriaService {
    //-----LISTAR CATEGORIAS-----
    List<Categoria> listarCategorias();

    //----BUSCAR CATEGORIA POR ID----
    Categoria buscarPorId(Long id);

    //-------CREAR CATEGORIA-----
    Categoria crear(CategoriaPeticionDto categoria);

    //-----MODIFICAR CATEGORIA-----
    Categoria modificar(Long id, CategoriaPeticionDto categoria);

    //------ELIMINAR CATEGORIA-----
    void eliminar(Long id);
}
