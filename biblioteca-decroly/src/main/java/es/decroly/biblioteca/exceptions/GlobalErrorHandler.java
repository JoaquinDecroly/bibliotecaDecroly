package es.decroly.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
    public class GlobalErrorHandler {

    //handler error recurso no encontrado
    @ExceptionHandler(NotFoundException.class)
        public ProblemDetail notFound(NotFoundException e){
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
                problem.setTitle("Recurso no encontrado");
            return problem;
        }

    //handler error petición errónea
    @ExceptionHandler(BadRequestException.class)
        public ProblemDetail badRequest(BadRequestException e){
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
                problem.setTitle("Petición errónea");
            return problem;
        }
}
