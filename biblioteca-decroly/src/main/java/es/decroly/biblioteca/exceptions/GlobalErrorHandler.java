package es.decroly.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la aplicación.
 * Anotado con @RestControllerAdvice para interceptar excepciones en todos los controladores REST.
 * Convierte las excepciones personalizadas en respuestas HTTP con formato ProblemDetail (RFC 7807).
 */
@RestControllerAdvice
    public class GlobalErrorHandler {

    /**
     * Maneja las excepciones de tipo NotFoundException.
     * Devuelve un ProblemDetail con estado HTTP 404 Not Found.
     *
     * @param e Excepción de recurso no encontrado
     * @return ProblemDetail con el detalle del error
     */
    //----HANDLER ERROR RECURSO NO ENCONTRADO----
    @ExceptionHandler(NotFoundException.class)
        public ProblemDetail notFound(NotFoundException e){
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
                problem.setTitle("Recurso no encontrado");
            return problem;
        }

    /**
     * Maneja las excepciones de tipo BadRequestException.
     * Devuelve un ProblemDetail con estado HTTP 400 Bad Request.
     *
     * @param e Excepción de petición incorrecta
     * @return ProblemDetail con el detalle del error
     */
    //-----HANDLER ERROR MALA PETICIÓN------
    @ExceptionHandler(BadRequestException.class)
        public ProblemDetail badRequest(BadRequestException e){
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
                problem.setTitle("Petición errónea");
            return problem;
        }
}
