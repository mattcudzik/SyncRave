import { HttpErrorResponse } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SessionService } from 'src/api/session.service'
import { GetSessionDTO } from 'src/model/getSessionDTO';
import { SessionPropertiesDTO } from 'src/model/sessionPropertiesDTO';
import { AddSessionDTO } from 'src/model/addSessionDTO';
import { CookieService } from 'ngx-cookie-service';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/api/auth.service';
import { ActivatedRoute } from '@angular/router';
import { SpotifyUserDTO } from 'src/model/spotifyUserDTO';

@Component({
  selector: 'app-admin-sessions',
  templateUrl: './admin-sessions.component.html',
  styleUrls: ['./admin-sessions.component.css']
}) 
export class AdminSessionsComponent implements OnInit{

  sessions: GetSessionDTO[] = [];
  isSessionLoading : boolean = true;
  disableButton: boolean = false;
  errorMessage?: String;

  createSessionForm = this.formBuilder.group({
    name:['',[Validators.required, Validators.minLength(5), Validators.maxLength(20), Validators.pattern("^[a-zA-Z0-9 ]+$")]],
    max_songs_per_guest: [-1, [Validators.min(-1), Validators.max(10000000)]],
    max_number_of_guests: [50, [Validators.required, Validators.min(1), Validators.max(100)]],
    ban_explicit_content: false,
    generate_playlist: true
  });

  constructor(
    private authService : AuthService,
    private sessionService : SessionService,
     private cookieService : CookieService,
     private route: ActivatedRoute,
     private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    if(!this.cookieService.check('token')) {
      this.route.queryParams.subscribe(params => {
        var code = params['code'];
        var error = params['error'];
        var state = params['state'];

        this.authService.callback(code, error, state).subscribe(
          (response: string) => {
            this.cookieService.set("token", response, 0.5);
            this.setup();
          }
        )
      } 
      )
    }
    else {
      this.setup();
    }
  }

  private setup(): void {
    let token = this.cookieService.get('token');

    this.sessionService.configuration.withCredentials = true;
    this.sessionService.configuration.credentials['bearerAuth'] = token;

    this.getSessions();
  }

  getSessions(): void {

    this.sessionService.getSesssions().subscribe(
      (response: GetSessionDTO[]) => {
        this.sessions = response;
        this.isSessionLoading = false;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    ) 
  }

  onCreateSessionFormSubmit(): void {
    var form = this.createSessionForm.value;
    this.disableButton = true;
    this.errorMessage = undefined;

    var requestBody: AddSessionDTO = {
      name: form.name!,
      properties: {
        ban_explicit_content: form.ban_explicit_content!,
        max_number_of_guests: form.max_number_of_guests!,
        max_songs_per_guest: form.max_songs_per_guest!,
        generate_playlist: form.generate_playlist!
      }
    }
    this.createSessionForm.reset({
      name: '',
      max_songs_per_guest: -1,
      max_number_of_guests: 50,
      ban_explicit_content: false,
      generate_playlist: true
    });
    this.createSessionForm.disable();

    this.sessionService.createSession(requestBody).subscribe(
      (response: GetSessionDTO) => {
        this.sessions.push(response);
        this.createSessionForm.enable();
        this.disableButton = false;
      },
      (error: HttpErrorResponse) => {
        this.createSessionForm.enable();
        this.disableButton = false;
        if(error.status == 400){
          this.errorMessage = error.message;
        }
      }
    );
    
  }
  
  onDeleteSession(id: string): void {
    this.sessionService.deleteSesssionById(id).subscribe(
      (response: any) => {
        this.sessions = this.sessions.filter((element, i) => element.id !== id)
      }
    );
  }

  public getRandomGradient() : string {
    const hue = Math.floor(Math.random() * 360);
    return "linear-gradient(to bottom right, hsl(" + hue + ", 100%, 50%), hsl(0, 0%, 100%))";
  }
}
