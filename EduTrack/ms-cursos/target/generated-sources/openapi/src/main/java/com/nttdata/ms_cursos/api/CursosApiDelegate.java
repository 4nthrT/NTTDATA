package com.nttdata.ms_cursos.api;

import com.nttdata.ms_cursos.model.Curso;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link CursosApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-14T16:30:34.734610500-05:00[America/Lima]", comments = "Generator version: 7.7.0")
public interface CursosApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /api/cursos : Lista los cursos, con filtro opcional por nivel
     *
     * @param nivel Filtra el catalogo por nivel. Si se omite, devuelve todos. (optional)
     * @return Lista de cursos (status code 200)
     * @see CursosApi#listarCursos
     */
    default ResponseEntity<List<Curso>> listarCursos(String nivel) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"horas\" : 20, \"titulo\" : \"Introduccion a Java\", \"id\" : 1, \"nivel\" : \"BASICO\" }, { \"horas\" : 20, \"titulo\" : \"Introduccion a Java\", \"id\" : 1, \"nivel\" : \"BASICO\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /api/cursos/{id} : Obtiene un curso por su id
     *
     * @param id Identificador del curso (required)
     * @return Curso encontrado (status code 200)
     *         or El curso no existe (status code 404)
     * @see CursosApi#obtenerCurso
     */
    default ResponseEntity<Curso> obtenerCurso(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"horas\" : 20, \"titulo\" : \"Introduccion a Java\", \"id\" : 1, \"nivel\" : \"BASICO\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
