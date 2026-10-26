# 🧱 domain

Entidades de la aplicación: los objetos que se guardan y se devuelven en las respuestas.

## 📖 Libro

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Long` | Identificador (lo asigna el servicio) |
| `isbn` | `String` | Código ISBN |
| `titulo` | `String` | Título |
| `autor` | `String` | Autor |
| `anioPublicacion` | `String` | Año de publicación |
| `ejemplares` | `int` | Número total de ejemplares |
| `disponibles` | `int` | Ejemplares disponibles para préstamo |
| `categoriaId` | `Long` | Id de la categoría a la que pertenece |

> Los constructores con parámetros inicializan `ejemplares` y `disponibles` a **1**.

## 🏷️ Categoria

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | `Long` | Identificador (lo asigna el servicio) |
| `nombre` | `String` | Nombre de la categoría |
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
```

📄 Documentación completa en el [README principal](../../../../../../../README.md)
