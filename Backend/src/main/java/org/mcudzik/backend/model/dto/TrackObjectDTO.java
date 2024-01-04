package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mcudzik.backend.model.dto.AlbumObjectDTO;
import org.mcudzik.backend.model.dto.ArtistObjectDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TrackObjectDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TrackObjectDTO {

  private AlbumObjectDTO album;

  @Valid
  private List<@Valid ArtistObjectDTO> artists = new ArrayList<>();

  private Integer durationMs;

  private String href;

  private String id;

  private String name;

  private String uri;

  private String url;

  public TrackObjectDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TrackObjectDTO(AlbumObjectDTO album, List<@Valid ArtistObjectDTO> artists, Integer durationMs, String href, String id, String name, String uri) {
    this.album = album;
    this.artists = artists;
    this.durationMs = durationMs;
    this.href = href;
    this.id = id;
    this.name = name;
    this.uri = uri;
  }

  public TrackObjectDTO album(AlbumObjectDTO album) {
    this.album = album;
    return this;
  }

  /**
   * Get album
   * @return album
  */
  @NotNull @Valid 
  @Schema(name = "album", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("album")
  public AlbumObjectDTO getAlbum() {
    return album;
  }

  public void setAlbum(AlbumObjectDTO album) {
    this.album = album;
  }

  public TrackObjectDTO artists(List<@Valid ArtistObjectDTO> artists) {
    this.artists = artists;
    return this;
  }

  public TrackObjectDTO addArtistsItem(ArtistObjectDTO artistsItem) {
    if (this.artists == null) {
      this.artists = new ArrayList<>();
    }
    this.artists.add(artistsItem);
    return this;
  }

  /**
   * The artists who performed the track. Each artist object includes a link in `href` to more detailed information about the artist. 
   * @return artists
  */
  @NotNull @Valid 
  @Schema(name = "artists", description = "The artists who performed the track. Each artist object includes a link in `href` to more detailed information about the artist. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("artists")
  public List<@Valid ArtistObjectDTO> getArtists() {
    return artists;
  }

  public void setArtists(List<@Valid ArtistObjectDTO> artists) {
    this.artists = artists;
  }

  public TrackObjectDTO durationMs(Integer durationMs) {
    this.durationMs = durationMs;
    return this;
  }

  /**
   * The track length in milliseconds. 
   * @return durationMs
  */
  @NotNull 
  @Schema(name = "duration_ms", description = "The track length in milliseconds. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("duration_ms")
  public Integer getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(Integer durationMs) {
    this.durationMs = durationMs;
  }

  public TrackObjectDTO href(String href) {
    this.href = href;
    return this;
  }

  /**
   * A link to the Web API endpoint providing full details of the track. 
   * @return href
  */
  @NotNull 
  @Schema(name = "href", description = "A link to the Web API endpoint providing full details of the track. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("href")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public TrackObjectDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the track. 
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the track. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TrackObjectDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the track. 
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the track. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TrackObjectDTO uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the track. 
   * @return uri
  */
  @NotNull 
  @Schema(name = "uri", description = "The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the track. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("uri")
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public TrackObjectDTO url(String url) {
    this.url = url;
    return this;
  }

  /**
   * The [Spotify URL](/documentation/web-api/concepts/spotify-uris-ids) for the object. 
   * @return url
  */
  
  @Schema(name = "url", description = "The [Spotify URL](/documentation/web-api/concepts/spotify-uris-ids) for the object. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrackObjectDTO trackObjectDTO = (TrackObjectDTO) o;
    return Objects.equals(this.album, trackObjectDTO.album) &&
        Objects.equals(this.artists, trackObjectDTO.artists) &&
        Objects.equals(this.durationMs, trackObjectDTO.durationMs) &&
        Objects.equals(this.href, trackObjectDTO.href) &&
        Objects.equals(this.id, trackObjectDTO.id) &&
        Objects.equals(this.name, trackObjectDTO.name) &&
        Objects.equals(this.uri, trackObjectDTO.uri) &&
        Objects.equals(this.url, trackObjectDTO.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(album, artists, durationMs, href, id, name, uri, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrackObjectDTO {\n");
    sb.append("    album: ").append(toIndentedString(album)).append("\n");
    sb.append("    artists: ").append(toIndentedString(artists)).append("\n");
    sb.append("    durationMs: ").append(toIndentedString(durationMs)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

