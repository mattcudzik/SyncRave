package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mcudzik.backend.model.dto.ImageObjectDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SpotifyUserDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SpotifyUserDTO {

  private String displayName;

  private String href;

  private String id;

  @Valid
  private List<@Valid ImageObjectDTO> images = new ArrayList<>();

  private String uri;

  public SpotifyUserDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SpotifyUserDTO(String href, String id, List<@Valid ImageObjectDTO> images, String uri) {
    this.href = href;
    this.id = id;
    this.images = images;
    this.uri = uri;
  }

  public SpotifyUserDTO displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * The name displayed on the user's profile. `null` if not available. 
   * @return displayName
  */
  
  @Schema(name = "display_name", description = "The name displayed on the user's profile. `null` if not available. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("display_name")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public SpotifyUserDTO href(String href) {
    this.href = href;
    return this;
  }

  /**
   * A link to the Web API endpoint for this user. 
   * @return href
  */
  @NotNull 
  @Schema(name = "href", description = "A link to the Web API endpoint for this user. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("href")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public SpotifyUserDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The [Spotify user ID](/documentation/web-api/concepts/spotify-uris-ids) for this user. 
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The [Spotify user ID](/documentation/web-api/concepts/spotify-uris-ids) for this user. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SpotifyUserDTO images(List<@Valid ImageObjectDTO> images) {
    this.images = images;
    return this;
  }

  public SpotifyUserDTO addImagesItem(ImageObjectDTO imagesItem) {
    if (this.images == null) {
      this.images = new ArrayList<>();
    }
    this.images.add(imagesItem);
    return this;
  }

  /**
   * The user's profile image. 
   * @return images
  */
  @NotNull @Valid 
  @Schema(name = "images", description = "The user's profile image. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("images")
  public List<@Valid ImageObjectDTO> getImages() {
    return images;
  }

  public void setImages(List<@Valid ImageObjectDTO> images) {
    this.images = images;
  }

  public SpotifyUserDTO uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for this user. 
   * @return uri
  */
  @NotNull 
  @Schema(name = "uri", description = "The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for this user. ", requiredMode = Schema.RequiredMode.REQUIRED)
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
    SpotifyUserDTO spotifyUserDTO = (SpotifyUserDTO) o;
    return Objects.equals(this.displayName, spotifyUserDTO.displayName) &&
        Objects.equals(this.href, spotifyUserDTO.href) &&
        Objects.equals(this.id, spotifyUserDTO.id) &&
        Objects.equals(this.images, spotifyUserDTO.images) &&
        Objects.equals(this.uri, spotifyUserDTO.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(displayName, href, id, images, uri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpotifyUserDTO {\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
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

