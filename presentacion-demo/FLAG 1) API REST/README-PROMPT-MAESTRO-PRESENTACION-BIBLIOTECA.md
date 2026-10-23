# PROMPT MAESTRO — Presentación web de Biblioteca Decroly

## Tu misión

Crea una **web de presentación moderna, impactante y profesional** para explicar y enseñar el proyecto Biblioteca Decroly, una API REST desarrollada con Spring Boot.

**No te estoy pidiendo que corrijas, refactorices ni mejores el backend.** El ZIP adjunto contiene el proyecto original y debe utilizarse como fuente de información para construir una presentación web independiente. Mantén intactos los archivos Java, la configuración, las pruebas, el `pom.xml` y el README original. Si necesitas crear la presentación dentro de la carpeta del proyecto, hazlo en una carpeta nueva y separada, por ejemplo `presentacion/`.

No te limites a proponer un diseño o devolver un boceto: crea los archivos de la presentación y deja una versión que pueda abrirse y mostrarse de verdad.

---

## 1. Antes de diseñar: entiende el proyecto

Inspecciona el ZIP y lee el `README.md`, `pom.xml` y las clases relevantes. El proyecto contiene, entre otros:

- Paquete base `es.decroly.biblioteca`.
- Controladores REST de libros y categorías.
- Dominio: `Libro` y `Categoria`.
- DTO de petición: `LibroPeticionDto` y `CategoriaPeticionDto`.
- Servicios e interfaces para libros y categorías.
- Implementaciones de servicios en memoria y mediante JSON.
- Gestión de excepciones y errores.
- Configuración de OpenAPI.
- Pruebas de servicios.

Verifica los nombres, campos, rutas, dependencias y comportamientos en los archivos reales. **No inventes funcionalidades, cifras, tecnologías, base de datos ni resultados de pruebas.** Si un dato no aparece en el código o la documentación, omítelo o márcalo claramente como no especificado.

El backend debe permanecer sin cambios. Puedes leer el código para explicar su arquitectura y mostrar ejemplos fieles, pero no debes arreglarlo ni reescribirlo.

## 2. Qué debe conseguir la presentación

La página debe servir para presentar el proyecto ante un profesor, compañeros o tribunal. Debe comunicar rápidamente:

- Qué es Biblioteca Decroly y qué problema resuelve.
- Qué tecnologías utiliza realmente.
- Cómo está organizada la aplicación.
- Cómo se separan controladores, DTO, dominio, servicios y manejo de errores.
- Cómo circula una petición HTTP por la aplicación.
- Qué endpoints ofrece la API.
- Cómo se representan libros y categorías.
- Qué papel tienen los servicios en memoria y JSON.
- Cómo consultar la documentación OpenAPI/Swagger, si está configurada y disponible.
- Qué pruebas existen, describiéndolas sin afirmar que se ejecutan correctamente si no se han ejecutado.

La web debe contar una historia visual ordenada, no ser una documentación técnica interminable.

## 3. Dirección visual

Diseña con criterio propio una identidad visual apropiada para una presentación de software de biblioteca. No uses la estética de SonicFlow ni una plantilla genérica de dashboard.

Dirección recomendada:

- Estética editorial y tecnológica, elegante y contemporánea.
- Fondo oscuro grafito o crema cálido, con un color de acento inspirado en bibliotecas, tinta, papel o tecnología; elige una paleta consistente.
- Tipografía con personalidad para títulos y una sans serif muy legible para texto e interfaz.
- Tarjetas limpias, jerarquía visual marcada, espacios generosos, líneas finas, pequeños detalles de código y diagramas.
- Iconografía consistente, preferiblemente Lucide si la integración es sencilla.
- Animaciones con intención: transiciones, aparición progresiva, movimiento sutil y estados interactivos.
- Diseño responsive para escritorio, portátil, tablet y móvil.
- Alto contraste, controles accesibles y respeto por `prefers-reduced-motion`.

Evita el aspecto de plantilla de administración, el exceso de tarjetas repetidas, los gradientes gratuitos, los efectos de neón por todas partes y los bloques de texto demasiado largos. La presentación debe sentirse diseñada para este proyecto concreto.

## 4. Estructura narrativa sugerida

Puedes cambiar el orden si mejora la historia, pero incluye estas secciones:

### A. Portada

- Nombre: **Biblioteca Decroly**.
- Subtítulo claro: API REST para gestionar libros y categorías.
- Sello visual con las tecnologías verificadas en el proyecto.
- Una composición atractiva que mezcle elementos editoriales de biblioteca con recursos gráficos de desarrollo.
- CTA para comenzar el recorrido o explorar la arquitectura.

### B. El proyecto en una mirada

Resume el propósito, las entidades principales y las capacidades reales. Puedes usar una composición visual de libro/categoría, pero no inventes métricas como usuarios, registros o rendimiento.

### C. Stack tecnológico

Muestra solo tecnologías y versiones que puedas confirmar en `pom.xml`, configuración o README. Explica brevemente para qué se usa cada una. No añadas tecnologías hipotéticas como JPA, Hibernate, una base de datos SQL, Docker o Spring Security si no forman parte del proyecto real.

### D. Arquitectura

Crea un diagrama visual legible de los componentes reales, basado en el código. Por ejemplo, si coincide con la implementación:

`Cliente HTTP → Controller → DTO / validación → Service → implementación en memoria o JSON`

Representa el dominio y la gestión global de errores como piezas relacionadas. Ajusta el diagrama a lo que demuestre el código. No presentes DTO como una capa que ejecuta lógica de negocio.

Haz que el diagrama tenga jerarquía, conectores claros y pequeñas explicaciones. No uses una imagen estática ilegible como sustituto de un diagrama real.

### E. Recorrido de una petición

Explica paso a paso un caso real, como consultar libros o crear una categoría:

1. El cliente realiza una petición HTTP.
2. El controlador recibe la petición.
3. Se procesan los datos de entrada mediante el DTO, según corresponda.
4. El servicio ejecuta la operación.
5. La implementación de almacenamiento responde.
6. El controlador devuelve la respuesta HTTP.

Adapta estos pasos a la lógica exacta del código. Añade un ejemplo JSON realista que respete los nombres y tipos de los campos existentes.

### F. Modelo de dominio

Presenta `Libro` y `Categoria` como tarjetas o fichas visuales, mostrando sus campos reales. Explica la relación mediante `categoriaId` solo si el código confirma ese vínculo. Distingue el modelo de dominio del DTO de petición.

### G. API REST / endpoints

Crea una sección visual con los endpoints reales obtenidos de los controladores. Incluye método HTTP, ruta, breve descripción y etiquetas por recurso. Distingue libros y categorías.

No copies ciegamente una tabla antigua del README: contrasta cada endpoint con los `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping` y `@DeleteMapping` reales. No inventes rutas ni códigos de respuesta. Puedes añadir filtros por método o recurso si funcionan de verdad.

### H. Servicios y almacenamiento

Explica las implementaciones existentes en memoria y JSON. Compara sus diferencias solo si puedes confirmarlas. Aclara cualquier limitación importante del almacenamiento en memoria o basado en ficheros, sin atribuirles persistencia o garantías que no tengan.

### I. Errores y documentación

Muestra cómo se organiza el manejo de errores y la configuración OpenAPI. Si incluyes un enlace a Swagger UI, construye la URL a partir de la configuración real y deja claro que solo funcionará si el servidor está arrancado.

### J. Pruebas y cierre

Describe las pruebas presentes y qué áreas cubren. No muestres porcentajes de cobertura ni afirmes que todas pasan sin evidencia. Termina con una síntesis visual de lo que demuestra el proyecto y, si aporta valor, un bloque con cómo arrancarlo.

---

## 5. Interacciones y animaciones

Quiero una presentación con acabado premium, no una página estática sin personalidad.

Usa las librerías solo cuando estén justificadas y sean compatibles con la solución elegida. Algunas opciones a evaluar:

- **GSAP + ScrollTrigger:** narración al hacer scroll, entradas de secciones y transiciones.
- **Anime.js:** microanimaciones y secuencias puntuales.
- **Lenis:** desplazamiento suave si mejora realmente la experiencia.
- **Three.js:** solo si una escena 3D aporta valor real; no es obligatorio.
- **Lucide:** iconografía coherente.
- **21st.dev / React Bits / otras skills de interfaz:** consulta si están disponibles y selecciona componentes útiles; no fuerces React en una web estática.

No cargues todas las librerías a la vez por cumplir una lista. Elige una estrategia técnica coherente con el formato final. Si el resultado es HTML independiente, no conviertas el proyecto entero a React o Next.js solo para usar una librería.

Ideas de movimiento:

- Portada con entrada de título y composición editorial.
- Diagrama de arquitectura que se revele por etapas.
- Flujo de petición que destaque cada capa al avanzar.
- Tarjetas de entidades con interacción sutil.
- Endpoints con filtros funcionales y estados hover/focus.
- Transiciones de sección suaves, sin marear ni bloquear el scroll.
- Navegación entre secciones con indicador de progreso.
- Pequeños detalles animados al copiar un ejemplo o cambiar un filtro, si se implementa esa interacción.

Todas las interacciones deben funcionar de verdad. No añadas botones decorativos que no hagan nada.

## 6. Formato y entrega

Prioriza una **presentación web independiente en HTML, CSS y JavaScript** que pueda abrirse fácilmente. Si una estructura de archivos separada es claramente mejor, crea una carpeta nueva con los recursos necesarios y un `README` propio para ejecutar la presentación.

Elige entre una solución de un solo HTML o varios archivos según lo que permita crear una presentación fiable, legible y fácil de mantener. No modifiques la aplicación Spring Boot para servir la presentación.

La página debe:

- Funcionar en navegadores modernos.
- Tener diseño responsive.
- Cargar sin errores de JavaScript.
- No depender de secretos, tokens ni servicios privados.
- Gestionar correctamente las librerías externas y explicar qué requiere conexión a Internet.
- Ofrecer una experiencia aceptable si falla una animación o no carga un recurso externo.
- Mantener el contenido legible sin animaciones.
- Respetar accesibilidad básica, navegación por teclado y movimiento reducido.

Si el entorno permite ejecutar la página y usar un navegador, hazlo y revisa el resultado. Comprueba consola, enlaces, filtros y navegación. Si no puedes realizar alguna comprobación, dilo claramente; no afirmes que se ha verificado.

## 7. Uso de herramientas y skills de Claude

Consulta las herramientas, integraciones y skills que estén realmente disponibles en tu sesión. Lee sus instrucciones antes de invocarlas.

Úsalas según su función:

- **21st.dev / React Bits:** inspiración o componentes de interfaz cuando encajen con el stack elegido.
- **Figma:** opcional para preparar o revisar un diseño si realmente mejora el resultado.
- **Whimsical:** opcional para planificar arquitectura o flujo, pero el resultado final debe incluir el diagrama dentro de la presentación.
- **GitHub:** solo si el usuario lo solicita o autoriza acceso al repositorio; no hagas commits, pushes ni publiques nada sin autorización.
- **Skills de diseño, animación y revisión visual:** úsalas si están disponibles y aportan valor.

No necesitas utilizar todas las integraciones. Prioriza la web terminada sobre el uso ornamental de herramientas.

## 8. Restricciones no negociables

- **No modificar el backend.**
- No cambiar clases Java, DTO, controladores, servicios, excepciones, dependencias, configuración, tests ni README original.
- No arreglar errores del proyecto como parte de esta tarea.
- No inventar endpoints, funcionalidades, tecnologías, métricas ni resultados de pruebas.
- No sustituir el contenido real por texto genérico.
- No dejar únicamente un plan, una auditoría o un mockup sin implementar.
- No añadir dependencias innecesarias.
- No publicar ni desplegar la web sin autorización expresa.

Si detectas un posible error en el backend que afecte a cómo debe explicarse la presentación, no lo corrijas. Describe con precisión el comportamiento que observas y, si hace falta, deja una nota en el informe final.

## 9. Forma de trabajo

1. Inspecciona el ZIP, el README y el código necesario.
2. Resume brevemente las funcionalidades y la arquitectura verificadas.
3. Propón una dirección visual concreta y una estructura de presentación.
4. Crea los archivos de la web en una carpeta independiente.
5. Implementa el diseño, los diagramas, las interacciones y las animaciones.
6. Verifica el contenido contra el código original.
7. Prueba la presentación si el entorno lo permite.
8. Entrega los archivos terminados e indica cómo abrirlos.

No te detengas después de proponer el diseño si puedes implementarlo directamente.

## 10. Resultado esperado

Quiero una presentación que parezca un proyecto profesional preparado para una exposición académica: narrativa clara, diseño cuidado, arquitectura visualmente comprensible, endpoints reales, ejemplos coherentes y animaciones de calidad.

Al terminar, resume:

- Dónde están los archivos de la presentación.
- Cómo abrirla o ejecutarla.
- Qué secciones e interacciones incluye.
- Qué herramientas y librerías utiliza.
- Qué verificaciones has realizado y cuáles no.
- Confirma explícitamente que el backend original se ha mantenido intacto.

**Criterio de éxito:** alguien que no haya leído el código debe poder entender Biblioteca Decroly en unos minutos y alguien técnico debe poder reconocer que la presentación refleja fielmente la aplicación real.
