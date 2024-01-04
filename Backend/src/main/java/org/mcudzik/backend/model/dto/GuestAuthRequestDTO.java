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
 * Properties of a guest
 */

@Schema(name = "GuestAuthRequestDTO", description = "Properties of a guest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GuestAuthRequestDTO {

  private String nickname;

  private String invitationCode;

  public GuestAuthRequestDTO nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  /**
   * Guest nickname
   * @return nickname
  */
  @Pattern(regexp = "^[a-zA-Z0-9]+$") @Size(min = 4, max = 10) 
  @Schema(name = "nickname", description = "Guest nickname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nickname")
  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public GuestAuthRequestDTO invitationCode(String invitationCode) {
    this.invitationCode = invitationCode;
    return this;
  }

  /**
   * Invitation code
   * @return invitationCode
  */
  @Pattern(regexp = "^[a-zA-Z0-9]+$") @Size(min = 6, max = 6) 
  @Schema(name = "invitation_code", description = "Invitation code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("invitation_code")
  public String getInvitationCode() {
    return invitationCode;
  }

  public void setInvitationCode(String invitationCode) {
    this.invitationCode = invitationCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GuestAuthRequestDTO guestAuthRequestDTO = (GuestAuthRequestDTO) o;
    return Objects.equals(this.nickname, guestAuthRequestDTO.nickname) &&
        Objects.equals(this.invitationCode, guestAuthRequestDTO.invitationCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname, invitationCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GuestAuthRequestDTO {\n");
    sb.append("    nickname: ").append(toIndentedString(nickname)).append("\n");
    sb.append("    invitationCode: ").append(toIndentedString(invitationCode)).append("\n");
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

