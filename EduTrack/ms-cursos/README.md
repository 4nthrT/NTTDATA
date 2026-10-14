# ms-cursos (EduTrack) — solución de referencia (uso interno del instructor)

Resultado correcto del caso práctico con resolución IA de **Open API**.
Es contra este contrato que se contrasta el `api.yaml` que entregue Copilot.

| Dato | Valor |
|---|---|
| Servicio | `ms-cursos` · puerto **8080** |
| Contrato | `src/main/resources/api.yaml` |
| Escrito a mano | **una sola clase**: `CursosApiDelegateImpl` |
| Swagger UI | http://localhost:8080/swagger-ui.html |

## Cómo ejecutarlo

```
mvn clean compile
mvn spring-boot:run
```

> **En IntelliJ**, tras el primer `compile` haz **↻ Reload All Maven Projects**.
> Sin eso el IDE marca en rojo `CursosApiDelegate` y `Curso` aunque el build sea correcto:
> todavía no ha indexado `target/generated-sources/openapi`.

## Qué genera el plugin y qué escribes tú

| Archivo | Quién lo crea |
|---|---|
| `CursosApi.java` · `CursosApiController.java` · `CursosApiDelegate.java` | el generador, desde `api.yaml` |
| `Curso.java` con el enum `NivelEnum` | el generador, desde `components/schemas` |
| `CursosApiDelegateImpl.java` | **tú**: es la lógica de negocio |
| `MsCursosApplication.java` | el arranque de Spring Boot |

Todo lo generado vive en `target/generated-sources/openapi` y **no se versiona**.

## Endpoints verificados

| URL | Status | Respuesta |
|---|---|---|
| `GET /api/cursos` | 200 | los 3 cursos |
| `GET /api/cursos?nivel=BASICO` | 200 | solo *Introduccion a Java* |
| `GET /api/cursos/1` | 200 | el curso 1 |
| `GET /api/cursos/99` | 404 | Not Found, sin cuerpo |

```json
{ "id": 1, "titulo": "Introduccion a Java", "nivel": "BASICO", "horas": 20 }
```

## Las dos configuraciones del pom que evitan errores

| Línea | Qué evita |
|---|---|
| `<supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>` | Que el generador cree su propia clase `main` y el build falle con *«Unable to find a single main class»*. |
| `<mainClass>com.nttdata.ms_cursos.MsCursosApplication</mainClass>` | Lo mismo, desde el lado de `spring-boot-maven-plugin`. |

## Los detalles del contrato que Copilot suele omitir

| Detalle | Por qué importa |
|---|---|
| `format: int64` en `id` | Sin él, el generador produce `Integer` en lugar de `Long`. |
| `required: false` en `nivel` | Sin él, el filtro pasa a ser obligatorio y `GET /api/cursos` devuelve 400. |
| `enum` en `nivel` | Es lo que genera `Curso.NivelEnum`. Sin él queda un `String` libre. |
| `required: [id, titulo, nivel]` | Marca los campos obligatorios del modelo. |
| La respuesta `404` documentada | Sin ella, el delegate no tiene contrato para el caso de no encontrado. |
| `$ref` a `components/schemas/Curso` | Define el modelo una vez y lo reutiliza; sin él se repite en cada respuesta. |

## Una nota sobre la firma de `listarCursos`

El `enum` del parámetro `nivel` está declarado **en línea** dentro del parámetro, igual que
en el contrato que se le pide al participante. Con esa forma, `openapi-generator` tipa el
parámetro como `String` y documenta los valores permitidos, que es contra lo que está
escrito `CursosApiDelegateImpl`:

```java
public ResponseEntity<List<Curso>> listarCursos(String nivel)
```

Si tu versión del generador lo tipara como un enum generado, lo único que cambia es esa
firma: la lógica del método sigue sirviendo. Tras el primer `mvn compile` puedes verlo en
`target/generated-sources/openapi/.../api/CursosApiDelegate.java`.
