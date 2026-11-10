# ⚙️ config

Configuración de la aplicación.

| Clase | Qué hace |
|---|---|
| `BibliotecaProperties` | `record` que lee las propiedades con prefijo `biblioteca` de `application.properties` |
| `OpenApiConfig` | Define el título, la versión, la descripción y el contacto que aparecen en Swagger |

## BibliotecaProperties

```java
@ConfigurationProperties(prefix = "biblioteca")
public record BibliotecaProperties(@DefaultValue("Biblioteca Decroly") String nombre,
                                   @DefaultValue("3") int maxPrestamos,
                                   @DefaultValue("15") int diasPrestamo) { }
```

| Propiedad (`application.properties`) | Campo del record | Para qué sirve | Valor por defecto |
|---|---|---|---|
| `biblioteca.nombre` | `nombre` | Nombre de la biblioteca | Biblioteca Decroly |
| `biblioteca.max-prestamos` | `maxPrestamos` | Número máximo de préstamos por persona | 3 |
| `biblioteca.dias-prestamo` | `diasPrestamo` | Días que dura un préstamo | 15 |

> Spring pasa de `max-prestamos` a `maxPrestamos` automáticamente. Se registra gracias a `@ConfigurationPropertiesScan` en la clase `main`. Si una propiedad no está en el fichero, se usa el valor de `@DefaultValue`.
>
> Solo la usa `BibliotecaServiceBase`, que devuelve estos valores en `GET /api/biblioteca/info`.

## OpenApiConfig

```java
@Configuration
@OpenAPIDefinition(info = @Info(title = "Biblioteca Decroly", version = "1.0.0",
        description = "API para gestionar categorías y libros",
        contact = @Contact(name = "...", url = "...")))
```

Swagger UI: `http://localhost:8080/swagger-ui.html`

📄 Documentación completa en el [README principal](../../../../../../../README.md)
