package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Properties of a session
 */

@Schema(name = "SessionPropertiesDTO", description = "Properties of a session")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SessionPropertiesDTO {

  private Integer maxSongsPerGuest = -1;

  private Integer maxNumberOfGuests = 50;

  private Boolean banExplicitContent = false;

  @Valid
  private List<String> bannedGenres = new ArrayList<>();

  private Boolean generatePlaylist = true;

  public SessionPropertiesDTO maxSongsPerGuest(Integer maxSongsPerGuest) {
    this.maxSongsPerGuest = maxSongsPerGuest;
    return this;
  }

  /**
   * Maximum number of songs a guest can add to queue
   * minimum: -1
   * maximum: 10000000
   * @return maxSongsPerGuest
  */
  @Min(-1) @Max(10000000) 
  @Schema(name = "max_songs_per_guest", description = "Maximum number of songs a guest can add to queue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("max_songs_per_guest")
  public Integer getMaxSongsPerGuest() {
    return maxSongsPerGuest;
  }

  public void setMaxSongsPerGuest(Integer maxSongsPerGuest) {
    this.maxSongsPerGuest = maxSongsPerGuest;
  }

  public SessionPropertiesDTO maxNumberOfGuests(Integer maxNumberOfGuests) {
    this.maxNumberOfGuests = maxNumberOfGuests;
    return this;
  }

  /**
   * Maximum number of guests that can join the session
   * minimum: 1
   * maximum: 100
   * @return maxNumberOfGuests
  */
  @Min(1) @Max(100) 
  @Schema(name = "max_number_of_guests", description = "Maximum number of guests that can join the session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("max_number_of_guests")
  public Integer getMaxNumberOfGuests() {
    return maxNumberOfGuests;
  }

  public void setMaxNumberOfGuests(Integer maxNumberOfGuests) {
    this.maxNumberOfGuests = maxNumberOfGuests;
  }

  public SessionPropertiesDTO banExplicitContent(Boolean banExplicitContent) {
    this.banExplicitContent = banExplicitContent;
    return this;
  }

  /**
   * Should explicit content be forbidden to be added to session
   * @return banExplicitContent
  */
  
  @Schema(name = "ban_explicit_content", description = "Should explicit content be forbidden to be added to session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ban_explicit_content")
  public Boolean getBanExplicitContent() {
    return banExplicitContent;
  }

  public void setBanExplicitContent(Boolean banExplicitContent) {
    this.banExplicitContent = banExplicitContent;
  }

  public SessionPropertiesDTO bannedGenres(List<String> bannedGenres) {
    this.bannedGenres = bannedGenres;
    return this;
  }

  public SessionPropertiesDTO addBannedGenresItem(String bannedGenresItem) {
    if (this.bannedGenres == null) {
      this.bannedGenres = new ArrayList<>();
    }
    this.bannedGenres.add(bannedGenresItem);
    return this;
  }

  /**
   * List of banned genres
   * @return bannedGenres
  */
  
  @Schema(name = "banned_genres", description = "List of banned genres", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("banned_genres")
  public List<String> getBannedGenres() {
    return bannedGenres;
  }

  public void setBannedGenres(List<String> bannedGenres) {
    this.bannedGenres = bannedGenres;
  }

  public SessionPropertiesDTO generatePlaylist(Boolean generatePlaylist) {
    this.generatePlaylist = generatePlaylist;
    return this;
  }

  /**
   * Should app generate and add to owners account playlist with all tracks that were added via session
   * @return generatePlaylist
  */
  
  @Schema(name = "generate_playlist", description = "Should app generate and add to owners account playlist with all tracks that were added via session", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("generate_playlist")
  public Boolean getGeneratePlaylist() {
    return generatePlaylist;
  }

  public void setGeneratePlaylist(Boolean generatePlaylist) {
    this.generatePlaylist = generatePlaylist;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionPropertiesDTO sessionPropertiesDTO = (SessionPropertiesDTO) o;
    return Objects.equals(this.maxSongsPerGuest, sessionPropertiesDTO.maxSongsPerGuest) &&
        Objects.equals(this.maxNumberOfGuests, sessionPropertiesDTO.maxNumberOfGuests) &&
        Objects.equals(this.banExplicitContent, sessionPropertiesDTO.banExplicitContent) &&
        Objects.equals(this.bannedGenres, sessionPropertiesDTO.bannedGenres) &&
        Objects.equals(this.generatePlaylist, sessionPropertiesDTO.generatePlaylist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxSongsPerGuest, maxNumberOfGuests, banExplicitContent, bannedGenres, generatePlaylist);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionPropertiesDTO {\n");
    sb.append("    maxSongsPerGuest: ").append(toIndentedString(maxSongsPerGuest)).append("\n");
    sb.append("    maxNumberOfGuests: ").append(toIndentedString(maxNumberOfGuests)).append("\n");
    sb.append("    banExplicitContent: ").append(toIndentedString(banExplicitContent)).append("\n");
    sb.append("    bannedGenres: ").append(toIndentedString(bannedGenres)).append("\n");
    sb.append("    generatePlaylist: ").append(toIndentedString(generatePlaylist)).append("\n");
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

