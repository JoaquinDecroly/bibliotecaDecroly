package es.decroly.biblioteca.services;

import es.decroly.biblioteca.domain.Categoria;
import es.decroly.biblioteca.exceptions.BadRequestException;
import es.decroly.biblioteca.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoriaServiceMemoria implements CategoriaService {
    private final List<Categoria> categorias = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong();

    @Override
    public synchronized List<Categoria> listarCategorias() {
        return categorias.stream().map(this::copiar).toList();
    }

    @Override
    public synchronized Categoria buscarPorId(Long id) {
        return copiar(buscar(id));
    }

    @Override
    public synchronized Categoria crear(Categoria categoria) {
        validar(categoria);
        validarNombreUnico(categoria.getNombre(), null);

        Categoria nueva = copiar(categoria);
        nueva.setId(secuencia.incrementAndGet());
        categorias.add(nueva);
        return copiar(nueva);
    }

    @Override
    public synchronized Categoria modificar(Long id, Categoria categoria) {
        Categoria existente = buscar(id);
        validar(categoria);
        validarNombreUnico(categoria.getNombre(), id);

        Categoria actualizada = copiar(categoria);
        actualizada.setId(id);
        categorias.set(categorias.indexOf(existente), actualizada);
        return copiar(actualizada);
    }

    @Override
    public synchronized void eliminar(Long id) {
        categorias.remove(buscar(id));
    }

    private void validar(Categoria categoria) {
        if (categoria == null) {
            throw new BadRequestException("La categoría es obligatoria");
        }
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de la categoría es obligatorio");
        }
    }

    private void validarNombreUnico(String nombre, Long idIgnorado) {
        boolean duplicado = categorias.stream().anyMatch(categoria ->
                !categoria.getId().equals(idIgnorado)
                        && categoria.getNombre().trim().equalsIgnoreCase(nombre.trim()));
        if (duplicado) {
            throw new BadRequestException("Ya existe una categoría con ese nombre");
        }
    }

    private Categoria buscar(Long id) {
        return categorias.stream()
                .filter(categoria -> categoria.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe la categoría con id " + id));
    }

    private Categoria copiar(Categoria categoria) {
        return new Categoria(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}
