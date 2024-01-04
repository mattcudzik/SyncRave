package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Invitation Entity
 */

@Schema(name = "SessionInvitationDTO", description = "Invitation Entity")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SessionInvitationDTO {

  private String code;

  private byte[] qrCode;

  public SessionInvitationDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SessionInvitationDTO(String code, byte[] qrCode) {
    this.code = code;
    this.qrCode = qrCode;
  }

  public SessionInvitationDTO code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Invitation code
   * @return code
  */
  @NotNull 
  @Schema(name = "code", description = "Invitation code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public SessionInvitationDTO qrCode(byte[] qrCode) {
    this.qrCode = qrCode;
    return this;
  }

  /**
   * QR code
   * @return qrCode
  */
  @NotNull 
  @Schema(name = "qrCode", description = "QR code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("qrCode")
  public byte[] getQrCode() {
    return qrCode;
  }

  public void setQrCode(byte[] qrCode) {
    this.qrCode = qrCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionInvitationDTO sessionInvitationDTO = (SessionInvitationDTO) o;
    return Objects.equals(this.code, sessionInvitationDTO.code) &&
        Arrays.equals(this.qrCode, sessionInvitationDTO.qrCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, Arrays.hashCode(qrCode));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionInvitationDTO {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    qrCode: ").append(toIndentedString(qrCode)).append("\n");
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

