# 📨 dto

**DTO = Data Transfer Object.** Son los datos que el cliente envía en el **body** al crear (`POST`) o modificar (`PUT`).

¿Por qué no usar directamente `Libro` o `Categoria`? Porque el cliente **no debe mandar** ni el `id` (lo genera el servicio) ni `disponibles` (lo controla la biblioteca).

```
JSON del body → @RequestBody → DTO → Service → crea / modifica la entidad
```

## 📖 LibroPeticionDto

| Campo | Tipo | ¿Obligatorio? |
|---|---|---|
| `isbn` | `String` | ✅ |
| `titulo` | `String` | ✅ |
| `autor` | `String` | ✅ |
| `anioPublicacion` | `String` | ❌ |
| `ejemplares` | `int` | ✅ (mínimo 1) |
| `categoriaId` | `Long` | ❌ |

```json
{
  "isbn": "9788491050292",
  "titulo": "Don Quijote de la Mancha",
  "autor": "Miguel de Cervantes",
  "anioPublicacion": "1605",
  "ejemplares": 2,
  "categoriaId": 16
}
```

## 🏷️ CategoriaPeticionDto

| Campo | Tipo | ¿Obligatorio? |
|---|---|---|
| `nombre` | `String` | ✅ |
| `descripcion` | `String` | ❌ |

```json
{
  "nombre": "Teatro",
  "descripcion": "Categoría de obras de teatro"
}
```

> La validación de los campos obligatorios se hace en el `ServiceBase` (método `validar`). Si falla, salta un **400 Bad Request**.

📄 Documentación completa en el [README principal](../../../../../../../README.md)
