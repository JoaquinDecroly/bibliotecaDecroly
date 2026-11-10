package es.decroly.biblioteca.exceptions;

/**
 * Excepción que representa un conflicto con los datos que ya existen (HTTP 409 Conflict).
 * Se lanza cuando se repite un dato que debe ser único: el nombre de una categoría o el ISBN de un libro.
 * El GlobalErrorHandler la convierte en una respuesta con el código 409.
 */
//-----ERROR CONFLICTO(409)------
public class ConflictoException extends RuntimeException {
    /**
     * Constructor que crea una excepción con un mensaje descriptivo.
     *
     * @param message Mensaje que describe el conflicto
     */
    public ConflictoException(String message) {
        super(message);
    }
}
