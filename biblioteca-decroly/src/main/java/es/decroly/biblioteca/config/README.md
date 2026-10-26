# ⚙️ config

Configuración de la aplicación.

| Clase | Qué hace |
|---|---|
| `BibliotecaProperties` | `record` que lee las propiedades con prefijo `biblioteca` de `application.properties` |
| `OpenApiConfig` | Define el título, la versión y la descripción que aparecen en Swagger |

## BibliotecaProperties

```java
@ConfigurationProperties(prefix = "biblioteca")
public record BibliotecaProperties(String nombre, int maxPrestamos, int diasPrestamo) { }
```

| Propiedad (`application.properties`) | Campo del record | Valor |
|---|---|---|
| `biblioteca.nombre` | `nombre` | Biblioteca Decroly |
| `biblioteca.max-prestamos` | `maxPrestamos` | 3 |
| `biblioteca.dias-prestamo` | `diasPrestamo` | 15 |

> Spring pasa de `max-prestamos` a `maxPrestamos` automáticamente. Se registra gracias a `@ConfigurationPropertiesScan` en la clase `main`, y se inyecta en el constructor de los servicios.

## OpenApiConfig

```java
@Configuration
@OpenAPIDefinition(info = @Info(title = "Biblioteca Decroly", version = "1.0.0",
                                description = "API para gestionar categorías y libros"))
```

Swagger UI: `http://localhost:8080/swagger-ui.html`

📄 Documentación completa en el [README principal](../../../../../../../README.md)
