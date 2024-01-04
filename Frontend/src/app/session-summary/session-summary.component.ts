import { DOCUMENT } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { SessionService } from 'src/api/session.service';
import { GetSessionDTO } from 'src/model/getSessionDTO';

@Component({
  selector: 'app-session-summary',
  templateUrl: './session-summary.component.html',
  styleUrls: ['./session-summary.component.css']
})
export class SessionSummaryComponent implements OnInit{
  sessionDTO?: GetSessionDTO;
  elem: any;
  id: string = "";

  constructor(
    private sessionService: SessionService,
    @Inject(DOCUMENT) private document: any,
    private route: ActivatedRoute,
    private cookieService : CookieService
  ){
    this.elem = document.documentElement;
    let token = cookieService.get("token");
    this.sessionService.configuration.withCredentials = true;
    this.sessionService.configuration.credentials['bearerAuth'] = token;
  }


  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.sessionService.getSessionById(this.id).subscribe(
      (response: GetSessionDTO) => {
        this.sessionDTO = response;
        console.log(response);
      },
      (error : HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  buttonClick() : void {
    if (this.elem.requestFullscreen) {
      this.elem.requestFullscreen();
    } else if (this.elem.mozRequestFullScreen) {
      /* Firefox */
      this.elem.mozRequestFullScreen();
    } else if (this.elem.webkitRequestFullscreen) {
      /* Chrome, Safari and Opera */
      this.elem.webkitRequestFullscreen();
    } else if (this.elem.msRequestFullscreen) {
      /* IE/Edge */
      this.elem.msRequestFullscreen();
    }
  }

}
