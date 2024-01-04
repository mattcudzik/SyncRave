import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { SessionService } from 'src/api/session.service';
import { GetSessionDTO } from 'src/model/getSessionDTO';
import { TrackObjectDTO } from 'src/model/trackObjectDTO';
import { HttpErrorResponse, HttpResponse, HttpResponseBase } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit{
  isAdmin: boolean = false;
  session?: GetSessionDTO;
  playlistURL?: String;

  id: string = "";
  queue : TrackObjectDTO[] = [];

  disableQueueButton : boolean = false;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private sessionService: SessionService,
    private cookieService : CookieService,
    private router: Router
  ) {
    let token = cookieService.get("token");
    if(token){
      const decodedToken : { [key: string]: any } = jwtDecode(token);
      this.isAdmin = decodedToken["role"] == "[SPOTIFY]";
    }
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.sessionService.getSessionById(this.id).subscribe(
      (response : GetSessionDTO) => {
        this.session = response;
        var ownerImg = this.session.owner.images.at(0)?.url;
        if(ownerImg != null){
          const regex = /https:\/\/platform-lookaside\.fbsbx\.com/g;
          this.session.owner.images.at(0)!.url = ownerImg.replace(regex, 'http://localhost:4200');
        }
      }
    );
    this.sessionService.getSessionPlaylist(this.id).subscribe(
      (response : String) => {
        this.playlistURL = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    )
    this.getQueue();
  }

  getQueue(): void {
    if(!this.disableQueueButton){
      this.disableQueueButton = true;
    this.sessionService.getSessionQueue(this.id).subscribe(
      (response : TrackObjectDTO[]) => {
        this.queue = response;
        this.disableQueueButton = false;
      }, 
      (error : HttpErrorResponse) => {
        this.disableQueueButton = false;
      }
    )
    }
  }

  onPlaylistButton() : void {
    if(this.playlistURL != null){
      window.location.href = this.playlistURL.toString();
    }
    
  }

  onTVMode() : void {
    console.log(this.route);
    this.router.navigate(["summary"], {relativeTo: this.route});
  }


}
