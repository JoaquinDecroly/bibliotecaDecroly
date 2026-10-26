# 🔌 controllers

Reciben las peticiones HTTP y llaman al servicio. **No tienen lógica**: solo conectan la URL con el método del servicio.

```
Petición HTTP → RestController → Service (interfaz) → devuelve JSON
```

| Clase | Ruta base | Servicios que usa |
|---|---|---|
| `LibroRestController` | `/api/libros` | `LibroService`, `CategoriaService` |
| `CategoriaRestController` | `/api/categorias` | `CategoriaService` |

Los servicios se reciben por el **constructor** (inyección de dependencias): Spring busca la clase con `@Service` que implementa la interfaz (`LibroServiceMemoria` / `CategoriaServiceMemoria`) y la pasa.

## 📖 LibroRestController

| Método | Ruta | Método Java | Llama a |
|---|---|---|---|
| `GET` | `/api/libros` | `listarLibros()` | `listarLibros()` |
| `GET` | `/api/libros/disponibles` | `listarLibrosDisponibles()` | `listarLibrosDisponibles()` |
| `GET` | `/api/libros/autor/{autor}` | `getLibroByAutor(autor)` | `listarLibrosDeAutor(autor)` |
| `GET` | `/api/libros/{id}/libros` | `listarLibrosCategoria(id)` | `categoriaService.buscarPorId(id)` + `obtenerLibrosDeCategoria(id)` |
| `GET` | `/api/libros/{id}` | `getLibroById(id)` | `buscarPorId(id)` |
| `POST` | `/api/libros` | `add(dto)` | `crear(dto)` |
| `PUT` | `/api/libros/{id}` | `modProd(id, dto)` | `modificar(id, dto)` |
| `DELETE` | `/api/libros/{id}` | `remove(id)` | `eliminar(id)` |

> En `/{id}/libros` primero se busca la categoría: si no existe, salta un **404** antes de buscar los libros.

## 🏷️ CategoriaRestController

| Método | Ruta | Método Java | Llama a |
|---|---|---|---|
| `GET` | `/api/categorias` | `listarCategorias()` | `listarCategorias()` |
| `GET` | `/api/categorias/{id}` | `getCategoriaById(id)` | `buscarPorId(id)` |
| `POST` | `/api/categorias` | `add(dto)` | `crear(dto)` |
| `PUT` | `/api/categorias/{id}` | `modProd(id, dto)` | `modificar(id, dto)` |
| `DELETE` | `/api/categorias/{id}` | `remove(id)` | `eliminar(id)` |

## 🏷️ Anotaciones

```java
// @RestController  -> Clase que devuelve JSON
// @RequestMapping  -> Ruta base del controller
// @GetMapping / @PostMapping / @PutMapping / @DeleteMapping -> Verbo HTTP
// @PathVariable    -> Valor de la ruta -> /libros/{id}
// @RequestBody     -> JSON del body a DTO
// @Tag             -> Grupo en Swagger
// @Operation       -> Descripción del endpoint en Swagger
// @ApiResponse     -> Respuestas posibles en Swagger
```

📄 Documentación completa en el [README principal](../../../../../../../README.md)
