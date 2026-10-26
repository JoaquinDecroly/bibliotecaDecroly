package es.decroly.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que representa un recurso no encontrado (HTTP 204 No Content).
 * Se lanza cuando se intenta acceder a un recurso que no existe en el sistema.
 * Anotada con @ResponseStatus para que Spring devuelva automáticamente el código 204.
 */
@ResponseStatus(HttpStatus.NO_CONTENT) //-----ERROR SIN CONTENIDO(204)-----
    public class NotFoundException extends RuntimeException {
    /**
     * Constructor que crea una excepción con un mensaje descriptivo.
     *
     * @param message Mensaje que describe el recurso no encontrado
     */
        public NotFoundException(String message) {
            super(message);
        }
    }
