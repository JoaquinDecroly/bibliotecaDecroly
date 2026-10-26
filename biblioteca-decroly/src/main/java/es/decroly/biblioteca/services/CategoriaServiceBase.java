package es.decroly.biblioteca.services;

import es.decroly.biblioteca.config.BibliotecaProperties;
import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Clase base que implementa la lógica común para el servicio de categorías.
 * Proporciona una implementación en memoria de las operaciones CRUD.
 * Las implementaciones específicas (Memoria, JSON) heredan de esta clase.
 */
public class CategoriaServiceBase implements CategoriaService{
    /** Lista de categorías almacenadas en memoria */
    private final List<Categoria> categorias;
    /** Generador de IDs secuenciales para nuevas categorías */
    private final AtomicLong secuencia;

    /**
     * Constructor que inicializa el servicio con una lista de categorías.
     * Calcula el ID máximo existente para continuar la secuencia.
     *
     * @param categoriasInd Lista inicial de categorías
     * @param properties Propiedades de configuración de la biblioteca
     */
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

    /**
     * Lista todas las categorías del sistema.
     *
     * @return Lista completa de categorías
     */
    //-----METODO LISTAR CATEGORIAS----
    @Override
    public List<Categoria> listarCategorias() {
        return categorias;
    }

    /**
     * Busca una categoría por su identificador.
     *
     * @param id Identificador de la categoría
     * @return Categoría encontrada
     * @throws NotFoundException si no existe una categoría con ese ID
     */
    //----METODO BUSCAR CATEGORIA POR ID------
    @Override
    public Categoria buscarPorId(Long id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró la categoría con id: " + id));
    }

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param categoria DTO con los datos de la categoría a crear
     * @return Categoría creada con su ID asignado
     * @throws BadRequestException si los datos son inválidos
     */
    //-----METODO CREAR CATEGORIA-----
    @Override
    public Categoria crear(CategoriaPeticionDto categoria) {
        validar(categoria);
        Long nId = secuencia.incrementAndGet();
        Categoria nueva = new Categoria(nId, categoria.getNombre(), categoria.getDescripcion());
        categorias.add(nueva);
        return nueva;
    }

    /**
     * Modifica una categoría existente.
     *
     * @param id Identificador de la categoría a modificar
     * @param categoria DTO con los nuevos datos de la categoría
     * @return Categoría modificada
     * @throws NotFoundException si no existe la categoría
     * @throws BadRequestException si los datos son inválidos
     */
    //----METODO MODIFICAR CATEGORIA----
    @Override
    public Categoria modificar(Long id, CategoriaPeticionDto categoria) {
        validar(categoria);
        Categoria categoriaMod = buscarPorId(id);
        categoriaMod.setNombre(categoria.getNombre());
        categoriaMod.setDescripcion(categoria.getDescripcion());
        return categoriaMod;
    }

    /**
     * Elimina una categoría del sistema.
     *
     * @param id Identificador de la categoría a eliminar
     * @throws NotFoundException si no existe la categoría
     */
    //------METODO ELIMINAR CATEGORIA-----
    @Override
    public void eliminar(Long id) {
        boolean remove = categorias.removeIf(categoria -> categoria.getId().equals(id));
        if (!remove) {
            throw new NotFoundException("No se encontró la categoría con id: " + id);
        }
    }
    
    /**
     * Valida los datos de una categoría antes de crear o modificar.
     *
     * @param dto DTO con los datos a validar
     * @throws BadRequestException si los datos son inválidos
     */
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
