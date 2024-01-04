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
import { TrackObjectDTO } from './trackObjectDTO';


/**
 * Spotify Player State Entity
 */
export interface CurrentPlayerStateDTO { 
    /**
     * Unix Millisecond Timestamp when data was fetched.
     */
    timestamp: number;
    /**
     * Progress into the currently playing track or episode. Can be `null`.
     */
    progress_ms: number;
    /**
     * If something is currently playing, return `true`.
     */
    is_playing: boolean;
    item: TrackObjectDTO;
}

