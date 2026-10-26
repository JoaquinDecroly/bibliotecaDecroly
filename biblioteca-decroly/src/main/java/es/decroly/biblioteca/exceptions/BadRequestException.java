package es.decroly.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que representa una petición incorrecta (HTTP 400 Bad Request).
 * Se lanza cuando los datos enviados por el cliente son inválidos o incompletos.
 * Anotada con @ResponseStatus para que Spring devuelva automáticamente el código 400.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST) //-----ERROR MALA PETICION(400)------
    public class BadRequestException extends RuntimeException {
    /**
     * Constructor que crea una excepción con un mensaje descriptivo.
     *
     * @param message Mensaje que describe el error de la petición
     */
        public BadRequestException(String message) {
            super(message);
        }
    }
