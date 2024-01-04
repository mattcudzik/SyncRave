package org.mcudzik.backend.model.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mcudzik.backend.model.dto.ArtistObjectDTO;
import org.mcudzik.backend.model.dto.ImageObjectDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AlbumObjectDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AlbumObjectDTO {

  @Valid
  private List<@Valid ArtistObjectDTO> artists = new ArrayList<>();

  private String id;

  private String name;

  private String releaseDate;

  @Valid
  private List<@Valid ImageObjectDTO> images = new ArrayList<>();

  private String uri;

  public AlbumObjectDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AlbumObjectDTO(List<@Valid ArtistObjectDTO> artists, String id, String name, String releaseDate, List<@Valid ImageObjectDTO> images, String uri) {
    this.artists = artists;
    this.id = id;
    this.name = name;
    this.releaseDate = releaseDate;
    this.images = images;
    this.uri = uri;
  }

  public AlbumObjectDTO artists(List<@Valid ArtistObjectDTO> artists) {
    this.artists = artists;
    return this;
  }

  public AlbumObjectDTO addArtistsItem(ArtistObjectDTO artistsItem) {
    if (this.artists == null) {
      this.artists = new ArrayList<>();
    }
    this.artists.add(artistsItem);
    return this;
  }

  /**
   * The artists of the album. Each artist object includes a link in `href` to more detailed information about the artist. 
   * @return artists
  */
  @NotNull @Valid 
  @Schema(name = "artists", description = "The artists of the album. Each artist object includes a link in `href` to more detailed information about the artist. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("artists")
  public List<@Valid ArtistObjectDTO> getArtists() {
    return artists;
  }

  public void setArtists(List<@Valid ArtistObjectDTO> artists) {
    this.artists = artists;
  }

  public AlbumObjectDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the album. 
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "2up3OPMp9Tb4dAKM2erWXQ", description = "The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the album. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AlbumObjectDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the album. In case of an album takedown, the value may be an empty string. 
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the album. In case of an album takedown, the value may be an empty string. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AlbumObjectDTO releaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  /**
   * The date the album was first released. 
   * @return releaseDate
  */
  @NotNull 
  @Schema(name = "release_date", example = "1981-12", description = "The date the album was first released. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("release_date")
  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public AlbumObjectDTO images(List<@Valid ImageObjectDTO> images) {
    this.images = images;
    return this;
  }

  public AlbumObjectDTO addImagesItem(ImageObjectDTO imagesItem) {
    if (this.images == null) {
      this.images = new ArrayList<>();
    }
    this.images.add(imagesItem);
    return this;
  }

  /**
   * The cover art for the album in various sizes, widest first. 
   * @return images
  */
  @NotNull @Valid 
  @Schema(name = "images", description = "The cover art for the album in various sizes, widest first. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("images")
  public List<@Valid ImageObjectDTO> getImages() {
    return images;
  }

  public void setImages(List<@Valid ImageObjectDTO> images) {
    this.images = images;
  }

  public AlbumObjectDTO uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the album. 
   * @return uri
  */
  @NotNull 
  @Schema(name = "uri", example = "spotify:album:2up3OPMp9Tb4dAKM2erWXQ", description = "The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the album. ", requiredMode = Schema.RequiredMode.REQUIRED)
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
    AlbumObjectDTO albumObjectDTO = (AlbumObjectDTO) o;
    return Objects.equals(this.artists, albumObjectDTO.artists) &&
        Objects.equals(this.id, albumObjectDTO.id) &&
        Objects.equals(this.name, albumObjectDTO.name) &&
        Objects.equals(this.releaseDate, albumObjectDTO.releaseDate) &&
        Objects.equals(this.images, albumObjectDTO.images) &&
        Objects.equals(this.uri, albumObjectDTO.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artists, id, name, releaseDate, images, uri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlbumObjectDTO {\n");
    sb.append("    artists: ").append(toIndentedString(artists)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
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

