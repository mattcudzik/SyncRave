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
import { ImageObjectDTO } from './imageObjectDTO';


export interface SpotifyUserDTO { 
    /**
     * The name displayed on the user\'s profile. `null` if not available. 
     */
    display_name?: string;
    /**
     * A link to the Web API endpoint for this user. 
     */
    href: string;
    /**
     * The [Spotify user ID](/documentation/web-api/concepts/spotify-uris-ids) for this user. 
     */
    id: string;
    /**
     * The user\'s profile image. 
     */
    images: Array<ImageObjectDTO>;
    /**
     * The [Spotify URI](/documentation/web-api/concepts/spotify-uris-ids) for this user. 
     */
    uri: string;
}

