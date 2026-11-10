# 🔌 controllers

Reciben las peticiones HTTP y llaman al servicio. **No tienen lógica**: solo conectan la URL con el método del servicio.

```
Petición HTTP → RestController → Service (interfaz) → devuelve JSON
```

| Clase | Ruta base | Servicios que usa |
|---|---|---|
| `LibroRestController` | `/api/libros` | `LibroService` |
| `CategoriaRestController` | `/api/categorias` | `CategoriaService`, `LibroService` |
| `BibliotecaRestController` | `/api/biblioteca` | `BibliotecaService` |

Los servicios se reciben por el **constructor** (inyección de dependencias), que genera Lombok con `@RequiredArgsConstructor` a partir de los atributos `final`: Spring busca la clase con `@Service` que implementa la interfaz (`LibroServiceMemoria` / `CategoriaServiceMemoria`) y la pasa.

## 📖 LibroRestController

| Método | Ruta | Método Java | Llama a | Respuestas |
|---|---|---|---|---|
| `GET` | `/api/libros` y `/api/libros?autor=` | `listarLibros(autor)` | `listarLibrosDeAutor(autor)` | 200 |
| `GET` | `/api/libros/disponibles` | `listarLibrosDisponibles()` | `listarLibrosDisponibles()` | 200 |
| `GET` | `/api/libros/{id}` | `getLibroById(id)` | `buscarPorId(id)` | 200 · 404 |
| `POST` | `/api/libros` | `add(dto)` | `crear(dto)` | 201 · 400 · 409 |
| `PUT` | `/api/libros/{id}` | `modProd(id, dto)` | `modificar(id, dto)` | 200 · 400 · 404 · 409 |
| `DELETE` | `/api/libros/{id}` | `remove(id)` | `eliminar(id)` | 204 · 404 |

> El filtro `?autor=` lo resuelve el servicio: si no llega autor devuelve todos los libros.

## 🏷️ CategoriaRestController

| Método | Ruta | Método Java | Llama a | Respuestas |
|---|---|---|---|---|
| `GET` | `/api/categorias` | `listarCategorias()` | `listarCategorias()` | 200 |
| `GET` | `/api/categorias/{id}` | `getCategoriaById(id)` | `buscarPorId(id)` | 200 · 404 |
| `GET` | `/api/categorias/{id}/libros` | `listarLibrosCategoria(id)` | `libroService.obtenerLibrosDeCategoria(id)` | 200 · 404 |
| `POST` | `/api/categorias` | `add(dto)` | `crear(dto)` | 201 · 400 · 409 |
| `PUT` | `/api/categorias/{id}` | `modProd(id, dto)` | `modificar(id, dto)` | 200 · 400 · 404 · 409 |
| `DELETE` | `/api/categorias/{id}` | `remove(id)` | `eliminar(id)` | 204 · 404 |

> En `/{id}/libros` es el **servicio** el que comprueba que la categoría existe: si no existe, lanza un **404**.

## 🏛️ BibliotecaRestController

| Método | Ruta | Método Java | Llama a | Respuestas |
|---|---|---|---|---|
| `GET` | `/api/biblioteca/info` | `obtenerInfoBiblioteca()` | `obtenerInfoBiblio()` | 200 |

## 🏷️ Anotaciones

```java
// @RestController  -> Clase que devuelve JSON
// @RequestMapping  -> Ruta base del controller
// @GetMapping / @PostMapping / @PutMapping / @DeleteMapping -> Verbo HTTP
// @RequiredArgsConstructor -> Lombok crea el constructor con los atributos final
// @PathVariable    -> Valor de la ruta -> /libros/{id}
// @RequestParam    -> Valor tras el ?  -> /libros?autor=Ana
// @RequestBody     -> JSON del body a DTO
// @ResponseStatus  -> Código de respuesta: 201 al crear, 204 al borrar
// @Tag             -> Grupo en Swagger
// @Operation       -> Descripción del endpoint en Swagger
// @ApiResponse     -> Respuestas posibles en Swagger
```

📄 Documentación completa en el [README principal](../../../../../../../README.md)
