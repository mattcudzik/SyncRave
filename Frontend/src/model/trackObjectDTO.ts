/**
 * Spotify Playlist Co-creation app
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { ArtistObjectDTO } from './artistObjectDTO';
import { AlbumObjectDTO } from './albumObjectDTO';


export interface TrackObjectDTO { 
    album: AlbumObjectDTO;
    /**
     * The artists who performed the track. Each artist object includes a link in `href` to more detailed information about the artist. 
     */
    artists: Array<ArtistObjectDTO>;
    /**
     * The track length in milliseconds. 
     */
    duration_ms: number;
    /**
     * A link to the Web API endpoint providing full details of the track. 
     */
    href: string;
    /**
     * The [Spotify ID](/documentation/web-api/concepts/spotify-uris-ids) for the track. 
     */
    id: string;
    /**
     * The name of the track. 
     */
    name: string;
    /**
     * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for the track. 
     */
    uri: string;
    /**
     * The [Spotify URL](/documentation/web-api/concepts/spotify-uris-ids) for the object. 
     */
    url?: string;
}

