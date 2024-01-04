package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.mcudzik.backend.model.dto.TrackObjectDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Spotify Player State Entity
 */

@Schema(name = "CurrentPlayerStateDTO", description = "Spotify Player State Entity")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CurrentPlayerStateDTO {

  private Long timestamp;

  private Integer progressMs;

  private Boolean isPlaying;

  private TrackObjectDTO item;

  public CurrentPlayerStateDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CurrentPlayerStateDTO(Long timestamp, Integer progressMs, Boolean isPlaying, TrackObjectDTO item) {
    this.timestamp = timestamp;
    this.progressMs = progressMs;
    this.isPlaying = isPlaying;
    this.item = item;
  }

  public CurrentPlayerStateDTO timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Unix Millisecond Timestamp when data was fetched.
   * @return timestamp
  */
  @NotNull 
  @Schema(name = "timestamp", description = "Unix Millisecond Timestamp when data was fetched.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public CurrentPlayerStateDTO progressMs(Integer progressMs) {
    this.progressMs = progressMs;
    return this;
  }

  /**
   * Progress into the currently playing track or episode. Can be `null`.
   * @return progressMs
  */
  @NotNull 
  @Schema(name = "progress_ms", description = "Progress into the currently playing track or episode. Can be `null`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("progress_ms")
  public Integer getProgressMs() {
    return progressMs;
  }

  public void setProgressMs(Integer progressMs) {
    this.progressMs = progressMs;
  }

  public CurrentPlayerStateDTO isPlaying(Boolean isPlaying) {
    this.isPlaying = isPlaying;
    return this;
  }

  /**
   * If something is currently playing, return `true`.
   * @return isPlaying
  */
  @NotNull 
  @Schema(name = "is_playing", description = "If something is currently playing, return `true`.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("is_playing")
  public Boolean getIsPlaying() {
    return isPlaying;
  }

  public void setIsPlaying(Boolean isPlaying) {
    this.isPlaying = isPlaying;
  }

  public CurrentPlayerStateDTO item(TrackObjectDTO item) {
    this.item = item;
    return this;
  }

  /**
   * Get item
   * @return item
  */
  @NotNull @Valid 
  @Schema(name = "item", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("item")
  public TrackObjectDTO getItem() {
    return item;
  }

  public void setItem(TrackObjectDTO item) {
    this.item = item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrentPlayerStateDTO currentPlayerStateDTO = (CurrentPlayerStateDTO) o;
    return Objects.equals(this.timestamp, currentPlayerStateDTO.timestamp) &&
        Objects.equals(this.progressMs, currentPlayerStateDTO.progressMs) &&
        Objects.equals(this.isPlaying, currentPlayerStateDTO.isPlaying) &&
        Objects.equals(this.item, currentPlayerStateDTO.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, progressMs, isPlaying, item);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CurrentPlayerStateDTO {\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    progressMs: ").append(toIndentedString(progressMs)).append("\n");
    sb.append("    isPlaying: ").append(toIndentedString(isPlaying)).append("\n");
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
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

