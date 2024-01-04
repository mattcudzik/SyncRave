package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.mcudzik.backend.model.dto.CurrentPlayerStateDTO;
import org.mcudzik.backend.model.dto.GetGuestDTO;
import org.mcudzik.backend.model.dto.SessionInvitationDTO;
import org.mcudzik.backend.model.dto.SessionPropertiesDTO;
import org.mcudzik.backend.model.dto.SpotifyUserDTO;
import org.springframework.format.annotation.DateTimeFormat;
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

@Schema(name = "GetSessionDTO", description = "Session Entity")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GetSessionDTO {

  private String name;

  private UUID id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date created;

  private SessionPropertiesDTO properties;

  private SpotifyUserDTO owner;

  private SessionInvitationDTO sessionInvitation;

  @Valid
  private List<@Valid GetGuestDTO> guests = new ArrayList<>();

  private CurrentPlayerStateDTO currentPlayerState;

  public GetSessionDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetSessionDTO(String name, UUID id, Date created, SessionPropertiesDTO properties, SpotifyUserDTO owner, SessionInvitationDTO sessionInvitation, List<@Valid GetGuestDTO> guests, CurrentPlayerStateDTO currentPlayerState) {
    this.name = name;
    this.id = id;
    this.created = created;
    this.properties = properties;
    this.owner = owner;
    this.sessionInvitation = sessionInvitation;
    this.guests = guests;
    this.currentPlayerState = currentPlayerState;
  }

  public GetSessionDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of session
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Name of session", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GetSessionDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Session ID
   * @return id
  */
  @NotNull @Valid 
  @Schema(name = "id", description = "Session ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public GetSessionDTO created(Date created) {
    this.created = created;
    return this;
  }

  /**
   * Created
   * @return created
  */
  @NotNull @Valid 
  @Schema(name = "created", description = "Created", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created")
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public GetSessionDTO properties(SessionPropertiesDTO properties) {
    this.properties = properties;
    return this;
  }

  /**
   * Get properties
   * @return properties
  */
  @NotNull @Valid 
  @Schema(name = "properties", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("properties")
  public SessionPropertiesDTO getProperties() {
    return properties;
  }

  public void setProperties(SessionPropertiesDTO properties) {
    this.properties = properties;
  }

  public GetSessionDTO owner(SpotifyUserDTO owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  */
  @NotNull @Valid 
  @Schema(name = "owner", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("owner")
  public SpotifyUserDTO getOwner() {
    return owner;
  }

  public void setOwner(SpotifyUserDTO owner) {
    this.owner = owner;
  }

  public GetSessionDTO sessionInvitation(SessionInvitationDTO sessionInvitation) {
    this.sessionInvitation = sessionInvitation;
    return this;
  }

  /**
   * Get sessionInvitation
   * @return sessionInvitation
  */
  @NotNull @Valid 
  @Schema(name = "sessionInvitation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sessionInvitation")
  public SessionInvitationDTO getSessionInvitation() {
    return sessionInvitation;
  }

  public void setSessionInvitation(SessionInvitationDTO sessionInvitation) {
    this.sessionInvitation = sessionInvitation;
  }

  public GetSessionDTO guests(List<@Valid GetGuestDTO> guests) {
    this.guests = guests;
    return this;
  }

  public GetSessionDTO addGuestsItem(GetGuestDTO guestsItem) {
    if (this.guests == null) {
      this.guests = new ArrayList<>();
    }
    this.guests.add(guestsItem);
    return this;
  }

  /**
   * Guests that joined session
   * @return guests
  */
  @NotNull @Valid 
  @Schema(name = "guests", description = "Guests that joined session", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("guests")
  public List<@Valid GetGuestDTO> getGuests() {
    return guests;
  }

  public void setGuests(List<@Valid GetGuestDTO> guests) {
    this.guests = guests;
  }

  public GetSessionDTO currentPlayerState(CurrentPlayerStateDTO currentPlayerState) {
    this.currentPlayerState = currentPlayerState;
    return this;
  }

  /**
   * Get currentPlayerState
   * @return currentPlayerState
  */
  @NotNull @Valid 
  @Schema(name = "currentPlayerState", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currentPlayerState")
  public CurrentPlayerStateDTO getCurrentPlayerState() {
    return currentPlayerState;
  }

  public void setCurrentPlayerState(CurrentPlayerStateDTO currentPlayerState) {
    this.currentPlayerState = currentPlayerState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetSessionDTO getSessionDTO = (GetSessionDTO) o;
    return Objects.equals(this.name, getSessionDTO.name) &&
        Objects.equals(this.id, getSessionDTO.id) &&
        Objects.equals(this.created, getSessionDTO.created) &&
        Objects.equals(this.properties, getSessionDTO.properties) &&
        Objects.equals(this.owner, getSessionDTO.owner) &&
        Objects.equals(this.sessionInvitation, getSessionDTO.sessionInvitation) &&
        Objects.equals(this.guests, getSessionDTO.guests) &&
        Objects.equals(this.currentPlayerState, getSessionDTO.currentPlayerState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, created, properties, owner, sessionInvitation, guests, currentPlayerState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetSessionDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    sessionInvitation: ").append(toIndentedString(sessionInvitation)).append("\n");
    sb.append("    guests: ").append(toIndentedString(guests)).append("\n");
    sb.append("    currentPlayerState: ").append(toIndentedString(currentPlayerState)).append("\n");
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

