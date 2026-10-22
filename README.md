# Biblioteca Decroly

API REST desarrollada con **Spring Boot** para gestionar el catálogo de una biblioteca. Permite consultar, crear, modificar y eliminar libros y categorías, así como filtrar libros por autor, categoría o disponibilidad.

## Requisitos

- **Java JDK 25**.
- **Maven** (opcional, el proyecto incluye Maven Wrapper).
- Un cliente HTTP, como Postman, Insomnia o `curl`, para probar los endpoints.

## Cómo arrancar la aplicación

1. Clona o descarga este repositorio.
2. Abre una terminal en la carpeta del proyecto y entra en la carpeta de la aplicación:

   ```bash
   cd biblioteca-decroly
   ```

3. Ejecuta la aplicación con el Maven Wrapper incluido.

   **Windows:**

   ```bat
   .\mvnw.cmd spring-boot:run
   ```

   **Linux / macOS:**

   ```bash
   ./mvnw spring-boot:run
   ```

   También puedes abrir el proyecto en IntelliJ IDEA y ejecutar la clase `BibliotecaDecrolyApplication`.

4. La API estará disponible en `http://localhost:8080`.

   La documentación interactiva de Swagger UI está disponible en `http://localhost:8080/swagger-ui/index.html`.

> El proyecto usa servicios en memoria o basados en ficheros JSON según la implementación de servicio configurada. Si se utiliza la implementación JSON, los ficheros `libros.json` y `categorias.json` deben estar disponibles en `src/main/resources`.

## Endpoints

Todos los endpoints devuelven o reciben datos en formato JSON cuando corresponde. `{id}` representa el identificador del recurso.

### Libros — `/api/libros`

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/libros` | Lista todos los libros. |
| `GET` | `/api/libros/disponibles` | Lista los libros que tienen ejemplares disponibles. |
| `GET` | `/api/libros/{id}` | Obtiene un libro por su ID. |
| `GET` | `/api/libros/autor/{autor}` | Lista los libros de un autor. Sustituye `{autor}` por el nombre del autor. |
| `GET` | `/api/libros/{id}/libros` | Lista los libros asociados a la categoría indicada por su ID. |
| `POST` | `/api/libros` | Crea un libro. Envía sus datos en el cuerpo de la petición. |
| `PUT` | `/api/libros/{id}` | Modifica el libro identificado por su ID. Envía los datos actualizados en el cuerpo. |
| `DELETE` | `/api/libros/{id}` | Elimina un libro por su ID. |

### Categorías — `/api/categorias`

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/categorias` | Lista todas las categorías. |
| `GET` | `/api/categorias/{id}` | Obtiene una categoría por su ID. |
| `POST` | `/api/categorias` | Crea una categoría. Envía sus datos en el cuerpo de la petición. |
| `PUT` | `/api/categorias/{id}` | Modifica la categoría identificada por su ID. Envía los datos actualizados en el cuerpo. |
| `DELETE` | `/api/categorias/{id}` | Elimina una categoría por su ID. |

### Ejemplos de cuerpos JSON

Crear una categoría (`POST /api/categorias`):

```json
{
  "nombre": "Ciencia ficción",
  "descripcion": "Novelas y relatos de ciencia ficción"
}
```

Crear un libro (`POST /api/libros`):

```json
{
  "isbn": "9780132350884",
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "anioPublicacion": "2008",
  "categoriaId": 1
}
```

Los campos del libro también incluyen `ejemplares` y `disponibles`; la aplicación puede asignarles valores iniciales al crear un libro.
