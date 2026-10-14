package com.nttdata.ms_cursos;

import com.nttdata.ms_cursos.api.CursosApiDelegate;
import com.nttdata.ms_cursos.model.Curso;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UNICA clase escrita a mano.
 *
 * Todo lo demas (CursosApi, CursosApiController, CursosApiDelegate y el modelo
 * Curso con su enum NivelEnum) lo genera openapi-generator desde api.yaml
 * en target/generated-sources/openapi.
 *
 * Si cambia el contrato, esta clase es lo unico que hay que ajustar.
 */
@Service
public class CursosApiDelegateImpl implements CursosApiDelegate {

    private final List<Curso> catalogo = List.of(
            curso(1L, "Introduccion a Java", Curso.NivelEnum.BASICO, 20),
            curso(2L, "Spring Boot en profundidad", Curso.NivelEnum.INTERMEDIO, 35),
            curso(3L, "Arquitectura de microservicios", Curso.NivelEnum.AVANZADO, 40));

    /** GET /api/cursos  . El parametro nivel es opcional (required: false). */
    @Override
    public ResponseEntity<List<Curso>> listarCursos(String nivel) {
        if (nivel == null || nivel.isBlank()) {
            return ResponseEntity.ok(catalogo);
        }
        List<Curso> filtrados = catalogo.stream()
                .filter(c -> c.getNivel() != null && c.getNivel().getValue().equalsIgnoreCase(nivel))
                .toList();
        return ResponseEntity.ok(filtrados);
    }

    /** GET /api/cursos/{id}  . 200 si existe, 404 si no (ambos documentados en el contrato). */
    @Override
    public ResponseEntity<Curso> obtenerCurso(Long id) {
        Optional<Curso> encontrado = catalogo.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return encontrado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private static Curso curso(Long id, String titulo, Curso.NivelEnum nivel, Integer horas) {
        Curso c = new Curso();
        c.setId(id);
        c.setTitulo(titulo);
        c.setNivel(nivel);
        c.setHoras(horas);
        return c;
    }
}
