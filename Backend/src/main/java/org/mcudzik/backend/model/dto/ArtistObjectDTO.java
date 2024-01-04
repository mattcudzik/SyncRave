package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ArtistObjectDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ArtistObjectDTO {

  private String href;

  private String id;

  private String name;

  private String uri;

  public ArtistObjectDTO href(String href) {
    this.href = href;
    return this;
  }

  /**
   * A link to the Web API endpoint providing full details of the artist. 
   * @return href
  */
  
  @Schema(name = "href", description = "A link to the Web API endpoint providing full details of the artist. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("href")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public ArtistObjectDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the artist. 
   * @return id
  */
  
  @Schema(name = "id", description = "The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the artist. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ArtistObjectDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the artist. 
   * @return name
  */
  
  @Schema(name = "name", description = "The name of the artist. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArtistObjectDTO uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the artist. 
   * @return uri
  */
  
  @Schema(name = "uri", description = "The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the artist. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uri")
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArtistObjectDTO artistObjectDTO = (ArtistObjectDTO) o;
    return Objects.equals(this.href, artistObjectDTO.href) &&
        Objects.equals(this.id, artistObjectDTO.id) &&
        Objects.equals(this.name, artistObjectDTO.name) &&
        Objects.equals(this.uri, artistObjectDTO.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, id, name, uri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArtistObjectDTO {\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
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

