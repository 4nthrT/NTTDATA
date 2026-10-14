package com.nttdata.ms_cursos.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Curso
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-14T16:30:34.734610500-05:00[America/Lima]", comments = "Generator version: 7.7.0")
public class Curso {

  private Long id;

  private String titulo;

  /**
   * Gets or Sets nivel
   */
  public enum NivelEnum {
    BASICO("BASICO"),
    
    INTERMEDIO("INTERMEDIO"),
    
    AVANZADO("AVANZADO");

    private String value;

    NivelEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NivelEnum fromValue(String value) {
      for (NivelEnum b : NivelEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private NivelEnum nivel;

  private Integer horas;

  public Curso() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Curso(Long id, String titulo, NivelEnum nivel) {
    this.id = id;
    this.titulo = titulo;
    this.nivel = nivel;
  }

  public Curso id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Curso titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
   */
  @NotNull 
  @Schema(name = "titulo", example = "Introduccion a Java", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Curso nivel(NivelEnum nivel) {
    this.nivel = nivel;
    return this;
  }

  /**
   * Get nivel
   * @return nivel
   */
  @NotNull 
  @Schema(name = "nivel", example = "BASICO", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nivel")
  public NivelEnum getNivel() {
    return nivel;
  }

  public void setNivel(NivelEnum nivel) {
    this.nivel = nivel;
  }

  public Curso horas(Integer horas) {
    this.horas = horas;
    return this;
  }

  /**
   * Get horas
   * @return horas
   */
  
  @Schema(name = "horas", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("horas")
  public Integer getHoras() {
    return horas;
  }

  public void setHoras(Integer horas) {
    this.horas = horas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Curso curso = (Curso) o;
    return Objects.equals(this.id, curso.id) &&
        Objects.equals(this.titulo, curso.titulo) &&
        Objects.equals(this.nivel, curso.nivel) &&
        Objects.equals(this.horas, curso.horas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, titulo, nivel, horas);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Curso {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    nivel: ").append(toIndentedString(nivel)).append("\n");
    sb.append("    horas: ").append(toIndentedString(horas)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

