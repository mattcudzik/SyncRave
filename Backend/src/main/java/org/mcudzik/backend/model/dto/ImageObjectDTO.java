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
 * ImageObjectDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ImageObjectDTO {

  private String url;

  private Integer height;

  private Integer width;

  public ImageObjectDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ImageObjectDTO(String url, Integer height, Integer width) {
    this.url = url;
    this.height = height;
    this.width = width;
  }

  public ImageObjectDTO url(String url) {
    this.url = url;
    return this;
  }

  /**
   * The source URL of the image. 
   * @return url
  */
  @NotNull 
  @Schema(name = "url", example = "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228 ", description = "The source URL of the image. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ImageObjectDTO height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * The image height in pixels. 
   * @return height
  */
  @NotNull 
  @Schema(name = "height", example = "300", description = "The image height in pixels. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("height")
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public ImageObjectDTO width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * The image width in pixels. 
   * @return width
  */
  @NotNull 
  @Schema(name = "width", example = "300", description = "The image width in pixels. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("width")
  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageObjectDTO imageObjectDTO = (ImageObjectDTO) o;
    return Objects.equals(this.url, imageObjectDTO.url) &&
        Objects.equals(this.height, imageObjectDTO.height) &&
        Objects.equals(this.width, imageObjectDTO.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, height, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImageObjectDTO {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
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

