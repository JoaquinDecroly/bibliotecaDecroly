# 📨 dto

**DTO = Data Transfer Object.** Son los datos que el cliente envía en el **body** al crear (`POST`) o modificar (`PUT`).

¿Por qué no usar directamente `Libro` o `Categoria`? Porque el cliente **no debe mandar** ni el `id` (lo genera el servicio) ni `disponibles` (lo controla la biblioteca).

```
JSON del body → @RequestBody → DTO → Service → crea / modifica la entidad
```

## 📖 LibroPeticionDto

| Campo | Tipo | ¿Obligatorio? |
|---|---|---|
| `isbn` | `String` | ✅ (13 cifras, no se repite) |
| `titulo` | `String` | ✅ |
| `autor` | `String` | ✅ |
| `anioPublicacion` | `Integer` | ❌ |
| `ejemplares` | `int` | ✅ (mínimo 1) |
| `categoriaId` | `Long` | ✅ (la categoría debe existir) |

```json
{
  "isbn": "9788491050292",
  "titulo": "Don Quijote de la Mancha",
  "autor": "Miguel de Cervantes",
  "anioPublicacion": 1605,
  "ejemplares": 2,
  "categoriaId": 16
}
```

## 🏷️ CategoriaPeticionDto

| Campo | Tipo | ¿Obligatorio? |
|---|---|---|
| `nombre` | `String` | ✅ (máximo 50 caracteres, no se repite) |
| `descripcion` | `String` | ❌ |

```json
{
  "nombre": "Teatro",
  "descripcion": "Categoría de obras de teatro"
}
```

## 🏛️ BibliotecaInfoDto

DTO de **salida**: es lo que devuelve `GET /api/biblioteca/info`.

| Campo | Tipo | Descripción |
|---|---|---|
| `nombre` | `String` | Nombre de la biblioteca |
| `maxPrestamos` | `int` | Número máximo de préstamos |
| `diasPrestamo` | `int` | Días que dura un préstamo |

> La validación se hace en el `ServiceBase` (métodos `validar`, `validarIsbnUnico` y `validarNombreUnico`). Si los datos no son válidos salta un **400 Bad Request**; si el ISBN o el nombre están repetidos, un **409 Conflict**.

📄 Documentación completa en el [README principal](../../../../../../../README.md)
