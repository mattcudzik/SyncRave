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
import { GetGuestDTO } from './getGuestDTO';
import { CurrentPlayerStateDTO } from './currentPlayerStateDTO';
import { SessionPropertiesDTO } from './sessionPropertiesDTO';
import { SessionInvitationDTO } from './sessionInvitationDTO';
import { SpotifyUserDTO } from './spotifyUserDTO';


/**
 * Session Entity
 */
export interface GetSessionDTO { 
    /**
     * Name of session
     */
    name: string;
    /**
     * Session ID
     */
    id: string;
    /**
     * Created
     */
    created: string;
    properties: SessionPropertiesDTO;
    owner: SpotifyUserDTO;
    sessionInvitation: SessionInvitationDTO;
    /**
     * Guests that joined session
     */
    guests: Array<GetGuestDTO>;
    currentPlayerState: CurrentPlayerStateDTO;
}
