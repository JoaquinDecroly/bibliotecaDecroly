# 📚 Biblioteca Decroly · API REST

API REST hecha con **Spring Boot** para gestionar los **libros** y las **categorías** de una biblioteca.
Los datos viven **en memoria** (o se pueden cargar desde un **fichero JSON**) y la API está documentada con **Swagger (OpenAPI)**.

---

## 🧭 Flujo de la aplicación

```mermaid
flowchart LR
   A["Cliente / Swagger"] -->|"Petición HTTP"| B["RestController"]
   B -->|"Llama a"| C["Service (interfaz)"]
   C -->|"Implementado en"| D["ServiceBase (lógica)"]
   D -->|"Hereda"| F["ServiceMemoria (@Service, activo)"]
   D -->|"Hereda"| E["ServiceJSON (sin activar)"]
   D -. "Error" .-> G["GlobalErrorHandler"]
   G -. "400 / 404 / 409 en JSON" .-> A
```

1. **El cliente** hace una petición HTTP (GET, POST, PUT, DELETE).
2. **El `RestController`** recibe la petición y llama al método del servicio que toca.
3. **El `Service` (interfaz)** declara qué métodos existen: listar, buscar, crear, modificar y eliminar.
4. **El `ServiceBase`** desarrolla esos métodos. Ahí está toda la lógica: trabaja sobre una lista y valida los datos.
5. **El `ServiceMemoria` y el `ServiceJSON`** heredan del `ServiceBase` y solo se encargan de **rellenar esa lista**:
   - `ServiceMemoria`: crea los libros y las categorías directamente en el código. **Es el que se usa**, porque lleva `@Service` y Spring lo inyecta en los controllers.
   - `ServiceJSON`: lee `libros.json` / `categorias.json` desde `resources`. Ahora mismo **no está activo**: no lleva `@Service` y los `.json` no existen todavía.
6. **Si algo falla**, el servicio lanza una excepción (`NotFoundException`, `BadRequestException` o `ConflictoException`) y el **`GlobalErrorHandler`** la convierte en una respuesta JSON con su código HTTP.

> El controller no sabe de dónde vienen los datos: solo habla con la interfaz `Service`. Así se puede cambiar de memoria a JSON sin tocar los controllers.

---

## 🗂️ Estructura del proyecto

```
biblioteca-decroly/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/es/decroly/biblioteca/
    │   │   ├── BibliotecaDecrolyApplication.java  → Arranque de la app
    │   │   ├── config/       → Propiedades y configuración de Swagger
    │   │   ├── controllers/  → Endpoints REST
    │   │   ├── domain/       → Entidades (Libro, Categoria)
    │   │   ├── dto/          → Datos que llegan en el body
    │   │   ├── exceptions/   → Excepciones y manejador global de errores
    │   │   └── services/     → Lógica de negocio
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/es/decroly/biblioteca/  → Tests de los servicios
├── docs/      → openapi.json (exportado de /v3/api-docs)
└── postman/   → Colección de Postman exportada (.json)
```

Cada carpeta tiene su propio `README.md` con el detalle de sus clases.

---

## 🧩 Clases principales

| Clase | Función |
|---|---|
| `BibliotecaDecrolyApplication` | Clase `main` que arranca Spring Boot |
| `BibliotecaProperties` | Lee las propiedades `biblioteca.*` de `application.properties` |
| `OpenApiConfig` | Título, versión, descripción y contacto de la documentación Swagger |
| `LibroRestController` / `CategoriaRestController` / `BibliotecaRestController` | Exponen los endpoints REST |
| `Libro` / `Categoria` | Entidades del dominio |
| `LibroPeticionDto` / `CategoriaPeticionDto` | Datos que llegan en el body al crear o modificar |
| `BibliotecaInfoDto` | Datos que devuelve `/api/biblioteca/info` |
| `LibroService` / `CategoriaService` / `BibliotecaService` | Interfaces con los métodos del servicio |
| `LibroServiceBase` / `CategoriaServiceBase` | Desarrollan los métodos y validan los datos |
| `BibliotecaServiceBase` | Devuelve la información de la biblioteca leyendo `BibliotecaProperties` |
| `LibroServiceMemoria` / `CategoriaServiceMemoria` | Cargan 40 libros y 40 categorías de prueba en memoria |
| `LibroServiceJSON` / `CategoriaServiceJSON` | Cargan los datos desde `libros.json` / `categorias.json` |
| `NotFoundException` | Se lanza cuando no existe el recurso → **404** |
| `BadRequestException` | Se lanza cuando los datos no son válidos → **400** |
| `ConflictoException` | Se lanza cuando se repite un ISBN o un nombre de categoría → **409** |
| `GlobalErrorHandler` | Captura las excepciones y devuelve el error en JSON |

---

## 🔌 Endpoints

### 📖 Libros (`/api/libros`)

| Método | Ruta | Descripción | Respuestas |
|---|---|---|---|
| `GET` | `/api/libros` | Lista todos los libros; con `?autor=` filtra por autor (contiene, sin distinguir mayúsculas) | 200 |
| `GET` | `/api/libros/disponibles` | Lista los libros con algún ejemplar disponible | 200 |
| `GET` | `/api/libros/{id}` | Obtiene un libro por id | 200 · 404 |
| `POST` | `/api/libros` | Crea un libro | 201 · 400 · 409 |
| `PUT` | `/api/libros/{id}` | Modifica un libro por id | 200 · 400 · 404 · 409 |
| `DELETE` | `/api/libros/{id}` | Elimina un libro por id | 204 · 404 |

### 🏷️ Categorías (`/api/categorias`)

| Método | Ruta | Descripción | Respuestas |
|---|---|---|---|
| `GET` | `/api/categorias` | Lista todas las categorías | 200 |
| `GET` | `/api/categorias/{id}` | Obtiene una categoría por id | 200 · 404 |
| `GET` | `/api/categorias/{id}/libros` | Lista los libros de una categoría | 200 · 404 |
| `POST` | `/api/categorias` | Crea una categoría | 201 · 400 · 409 |
| `PUT` | `/api/categorias/{id}` | Modifica una categoría por id | 200 · 400 · 404 · 409 |
| `DELETE` | `/api/categorias/{id}` | Elimina una categoría por id | 204 · 404 |

### 🏛️ Biblioteca (`/api/biblioteca`)

| Método | Ruta | Descripción | Respuestas |
|---|---|---|---|
| `GET` | `/api/biblioteca/info` | Nombre de la biblioteca, máximo de préstamos y días de préstamo configurados | 200 |

### 📏 Reglas

| Recurso | Reglas |
|---|---|
| Categoría | `nombre` obligatorio, máximo 50 caracteres y no se repite (sin distinguir mayúsculas) |
| Libro | `isbn` de 13 cifras y no se repite · `titulo` y `autor` obligatorios · `ejemplares` ≥ 1 · `categoriaId` debe existir |
| Libro | Al crear, `disponibles` = `ejemplares`. Al modificar los ejemplares, los disponibles se actualizan |

### 📨 Ejemplos de body

```json
// POST /api/libros
{
  "isbn": "9788491050292",
  "titulo": "Don Quijote de la Mancha",
  "autor": "Miguel de Cervantes",
  "anioPublicacion": 1605,
  "ejemplares": 2,
  "categoriaId": 16
}
```

```json
// POST /api/categorias
{
  "nombre": "Teatro",
  "descripcion": "Categoría de obras de teatro"
}
```

### ⚠️ Respuestas de error

| Código | Cuándo |
|---|---|
| `400 Bad Request` | Faltan datos obligatorios (ISBN, título, autor, nombre…), ISBN sin 13 cifras, nombre de más de 50 caracteres, `ejemplares < 1` o `categoriaId` que no existe |
| `404 Not Found` | No existe el libro o la categoría con ese id |
| `409 Conflict` | El ISBN o el nombre de la categoría ya existe |

Los errores salen en formato `ProblemDetail`:

```json
{
  "type": "about:blank",
  "title": "Recurso no encontrado",
  "status": 404,
  "detail": "No se encontró el libro con id: 99",
  "instance": "/api/libros/99"
}
```

---

## ⚙️ Configuración

`src/main/resources/application.properties`:

```properties
spring.application.name=biblioteca-decroly
biblioteca.nombre=Biblioteca Decroly
biblioteca.max-prestamos=3
biblioteca.dias-prestamo=15
server.port=8080
spring.mvc.problemdetails.enabled=true
```

| Propiedad | Para qué sirve | Valor por defecto |
|---|---|---|
| `spring.application.name` | Nombre de la aplicación en Spring | — |
| `biblioteca.nombre` | Nombre de la biblioteca que devuelve `/api/biblioteca/info` | `Biblioteca Decroly` |
| `biblioteca.max-prestamos` | Número máximo de préstamos que puede tener una persona a la vez | `3` |
| `biblioteca.dias-prestamo` | Días que dura un préstamo | `15` |
| `server.port` | Puerto en el que arranca la aplicación | `8080` |
| `spring.mvc.problemdetails.enabled` | Hace que los errores del propio Spring (JSON mal formado, id que no es un número…) también salgan como `ProblemDetail` | `false` |

Spring carga las propiedades `biblioteca.*` en el record `BibliotecaProperties` (gracias a `@ConfigurationPropertiesScan`). Si alguna no está en el fichero, se usa el valor por defecto indicado con `@DefaultValue` en el propio record. El endpoint `GET /api/biblioteca/info` devuelve los valores cargados:

```json
{
  "nombre": "Biblioteca Decroly",
  "maxPrestamos": 3,
  "diasPrestamo": 15
}
```

---

## 🏷️ Anotaciones usadas

```java
// ---- Spring Web ----
// @RestController      -> Clase que devuelve JSON
// @RequestMapping      -> Ruta base del controller
// @GetMapping          -> LEER datos
// @PostMapping         -> CREAR recurso
// @PutMapping          -> MODIFICAR recurso
// @DeleteMapping       -> BORRAR recurso
// @PathVariable        -> Valor de la ruta  -> /libros/{id}
// @RequestParam        -> Valor tras el ?   -> /libros?autor=Ana
// @ResponseStatus      -> Fija el código HTTP de respuesta (201, 204)
// @RequestBody         -> JSON del body a objeto Java

// ---- Spring ----
// @Service             -> Spring crea el servicio y lo inyecta en los controllers
// @Configuration       -> Clase de configuración
// @ConfigurationProperties -> Lee propiedades con un prefijo
// @RestControllerAdvice    -> Manejador global de errores
// @ExceptionHandler        -> Qué hacer con cada excepción

// ---- Swagger ----
// @Tag / @Operation / @ApiResponse / @OpenAPIDefinition -> Documentación
// @Schema             -> Descripción y ejemplo de cada campo

// ---- Lombok ----
// @Getter / @Setter / @NoArgsConstructor / @AllArgsConstructor -> Genera código repetitivo
// @RequiredArgsConstructor -> Constructor con los atributos final (inyección por constructor)
```

---

## ▶️ Cómo ejecutarlo

```bash
git clone https://github.com/JoaquinDecroly/bibliotecaDecroly.git
cd bibliotecaDecroly/biblioteca-decroly
./mvnw spring-boot:run
```

En Windows: `mvnw.cmd spring-boot:run`

Una vez arrancada la aplicación, la documentación interactiva de Swagger está en:

```
http://localhost:8080/swagger-ui.html
```

El JSON de OpenAPI está en `http://localhost:8080/v3/api-docs` y exportado en `docs/openapi.json`.

### 🧪 Tests

```bash
./mvnw test
```

### 📮 Postman

La colección exportada está en `postman/Biblioteca Decroly.postman_collection.json`. Con la aplicación arrancada, impórtala en Postman y ejecútala entera con el **Runner**.

- Usa la variable `baseUrl` (`http://localhost:8080`).
- Cada petición comprueba su código de estado e incluye los casos de error (400, 404 y 409).
- Guarda en variables el id de la categoría y del libro creados (`categoriaId`, `libroId`) y los usa en las peticiones siguientes.
- Al final borra lo que ha creado, así que se puede repetir sin reiniciar la aplicación.

---

## 🛠️ Tecnologías

- **Java 25**
- **Spring Boot 4.1.1** (Spring Web MVC)
- **SpringDoc OpenAPI 3.1.0** (Swagger UI)
- **Jackson 3** (lectura de JSON)
- **Lombok**
- **JUnit 5**
- **Maven**

---

👤 **Autor:** Joaquín Valentín Ottonello · [@JoaquinDecroly](https://github.com/JoaquinDecroly)
