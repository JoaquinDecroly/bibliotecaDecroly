# ⚠️ exceptions

Gestión de errores de la API.

```
Service lanza excepción → GlobalErrorHandler la captura → Respuesta JSON con código HTTP
```

| Clase | Qué hace | Código |
|---|---|---|
| `NotFoundException` | Se lanza cuando no existe el libro o la categoría | **404** |
| `BadRequestException` | Se lanza cuando los datos enviados no son válidos | **400** |
| `ConflictoException` | Se lanza cuando se repite un ISBN o un nombre de categoría | **409** |
| `GlobalErrorHandler` | Captura las tres excepciones en toda la app y devuelve un `ProblemDetail` | — |

> Las excepciones **no** llevan `@ResponseStatus`: el código HTTP lo decide solo el `GlobalErrorHandler`.

## ¿Cuándo salta cada una?

| Excepción | Ejemplo |
|---|---|
| `NotFoundException` | `GET /api/libros/99` y no existe el libro 99 |
| `BadRequestException` | `POST /api/libros` sin título, sin autor, con un ISBN que no tiene 13 cifras, con `ejemplares: 0` o con una categoría que no existe |
| `ConflictoException` | `POST /api/libros` con un ISBN que ya tiene otro libro, o `POST /api/categorias` con un nombre que ya existe |

## GlobalErrorHandler

```java
// @RestControllerAdvice -> Se aplica a TODOS los controllers
// @ExceptionHandler(X)  -> Método que se ejecuta cuando salta la excepción X

// NotFoundException   -> 404 + título "Recurso no encontrado"
// BadRequestException -> 400 + título "Petición errónea"
// ConflictoException  -> 409 + título "Conflicto"
```

Ejemplo de respuesta:

```json
{
  "type": "about:blank",
  "title": "Recurso no encontrado",
  "status": 404,
  "detail": "No se encontró el libro con id: 99",
  "instance": "/api/libros/99"
}
```

📄 Documentación completa en el [README principal](../../../../../../../README.md)
