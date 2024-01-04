package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.mcudzik.backend.model.dto.SessionPropertiesDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Session Entity
 */

@Schema(name = "AddSessionDTO", description = "Session Entity")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AddSessionDTO {

  private String name;

  private SessionPropertiesDTO properties;

  public AddSessionDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AddSessionDTO(String name) {
    this.name = name;
  }

  public AddSessionDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of session
   * @return name
  */
  @NotNull @Pattern(regexp = "^[a-zA-Z0-9 ]+$") @Size(min = 5, max = 20) 
  @Schema(name = "name", description = "Name of session", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AddSessionDTO properties(SessionPropertiesDTO properties) {
    this.properties = properties;
    return this;
  }

  /**
   * Get properties
   * @return properties
  */
  @Valid 
  @Schema(name = "properties", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("properties")
  public SessionPropertiesDTO getProperties() {
    return properties;
  }

  public void setProperties(SessionPropertiesDTO properties) {
    this.properties = properties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddSessionDTO addSessionDTO = (AddSessionDTO) o;
    return Objects.equals(this.name, addSessionDTO.name) &&
        Objects.equals(this.properties, addSessionDTO.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddSessionDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

