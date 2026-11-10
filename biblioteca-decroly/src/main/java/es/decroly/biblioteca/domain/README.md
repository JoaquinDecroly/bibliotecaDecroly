# 🧱 domain

Entidades de la aplicación: los objetos que se guardan y se devuelven en las respuestas.

## 📖 Libro

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Long` | Identificador (lo asigna el servicio) |
| `isbn` | `String` | Código ISBN (13 cifras, no se repite) |
| `titulo` | `String` | Título |
| `autor` | `String` | Autor |
| `anioPublicacion` | `Integer` | Año de publicación |
| `ejemplares` | `int` | Número total de ejemplares |
| `disponibles` | `int` | Ejemplares disponibles para préstamo |
| `categoriaId` | `Long` | Id de la categoría a la que pertenece |

> El constructor de 6 parámetros (sin `ejemplares` ni `disponibles`) los inicializa a **1** y solo se usa para los libros de prueba. Al crear un libro desde la API, el servicio usa el constructor completo: `disponibles` = `ejemplares`.

## 🏷️ Categoria

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Long` | Identificador (lo asigna el servicio) |
| `nombre` | `String` | Nombre de la categoría (máximo 50 caracteres, no se repite) |
| `descripcion` | `String` | Descripción |

## 🔗 Relación

```
Categoria (1) ──── (N) Libro      → a través de Libro.categoriaId
```

## 🏷️ Anotaciones Lombok

```java
// @Getter             -> Genera los getters
// @Setter             -> Genera los setters
// @NoArgsConstructor  -> Constructor vacío (lo necesita Jackson para leer JSON)
// @AllArgsConstructor -> Constructor con todos los campos
// @Schema (Swagger)   -> Descripción y ejemplo de cada campo en la documentación
```

📄 Documentación completa en el [README principal](../../../../../../../README.md)
