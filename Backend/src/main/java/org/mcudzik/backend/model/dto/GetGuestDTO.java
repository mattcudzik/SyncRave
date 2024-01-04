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
 * Guest Entity
 */

@Schema(name = "GetGuestDTO", description = "Guest Entity")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GetGuestDTO {

  private String nickname;

  private Long numSongsAdded;

  public GetGuestDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetGuestDTO(String nickname) {
    this.nickname = nickname;
  }

  public GetGuestDTO nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  /**
   * Get nickname
   * @return nickname
  */
  @NotNull 
  @Schema(name = "nickname", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nickname")
  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public GetGuestDTO numSongsAdded(Long numSongsAdded) {
    this.numSongsAdded = numSongsAdded;
    return this;
  }

  /**
   * Get numSongsAdded
   * @return numSongsAdded
  */
  
  @Schema(name = "num_songs_added", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("num_songs_added")
  public Long getNumSongsAdded() {
    return numSongsAdded;
  }

  public void setNumSongsAdded(Long numSongsAdded) {
    this.numSongsAdded = numSongsAdded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetGuestDTO getGuestDTO = (GetGuestDTO) o;
    return Objects.equals(this.nickname, getGuestDTO.nickname) &&
        Objects.equals(this.numSongsAdded, getGuestDTO.numSongsAdded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname, numSongsAdded);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetGuestDTO {\n");
    sb.append("    nickname: ").append(toIndentedString(nickname)).append("\n");
    sb.append("    numSongsAdded: ").append(toIndentedString(numSongsAdded)).append("\n");
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

