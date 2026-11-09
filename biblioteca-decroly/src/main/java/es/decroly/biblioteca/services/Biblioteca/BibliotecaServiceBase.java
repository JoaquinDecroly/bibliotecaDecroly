package es.decroly.biblioteca.services.Biblioteca;

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
public class BibliotecaServiceBase implements BibliotecaService {
    @Override
    public String obtenerInfoBiblio() {
        return ;
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
