import { Component, Input, OnInit } from '@angular/core';
import { PlayerService } from 'src/api/player.service';
import { CurrentPlayerStateDTO } from 'src/model/currentPlayerStateDTO';
import { CookieService } from 'ngx-cookie-service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent {

  currentPlayerState? : CurrentPlayerStateDTO;

  constructor(private playerService: PlayerService,  private cookieService : CookieService) {
    
    let token = this.cookieService.get('token');
    this.playerService.configuration.withCredentials = true;
    this.playerService.configuration.credentials['bearerAuth'] = token;

    this.updatePlayerState();
  }

  onPrevius() : void {
    this.playerService.previousSong().subscribe(
      (response: any) => {
        this.updatePlayerState();
      }
    );
  }

  onNext() : void { 
    this.playerService.skipSong().subscribe(
      (response: any) => {
          this.updatePlayerState();
      }
    );
  }

  onPlayPause() : void {

    this.playerService.getState().subscribe(
      (response: CurrentPlayerStateDTO) => {
        this.currentPlayerState = response;
        if(response.is_playing) {
          this.playerService.pauseSong().subscribe()
        }
        else {
          this.playerService.playSong().subscribe()
        }
      }
    )

  }

  onChangeVolume(event: any): void {
    
    let volume =  event.target.value;
    console.log(volume);
    this.playerService.setVolume(volume).subscribe();
  }

  updatePlayerState(): void {
    this.playerService.getState().subscribe(
      (response : CurrentPlayerStateDTO) => {
        this.currentPlayerState = response;
      }
    )
  }
}
