import { Component, HostBinding, Inject, OnInit } from '@angular/core';
import { AuthService } from 'src/api/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { DOCUMENT } from '@angular/common';
import { FormBuilder, Validators } from '@angular/forms';
import { GetSessionDTO } from 'src/model/getSessionDTO';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {
  @HostBinding('class.App') hostClass = true;

  errorMessage?: String;
  disableSendButton: boolean = false;

  joinSessionForm = this.formBuilder.group(
    {
      pin: ['', [Validators.required,  Validators.minLength(6), Validators.maxLength(6), Validators.pattern("^[a-zA-Z0-9]+$")]],
      nickname: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(10), Validators.pattern("^[a-zA-Z0-9]+$")]]
    }
  );

  constructor(
    private authService : AuthService,
    private route: ActivatedRoute,
    private cookieService : CookieService,
    @Inject(DOCUMENT) private document: Document,
    private formBuilder: FormBuilder,
    private router: Router) {

      this.authService.configuration.withCredentials = true;
  }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.has("pin")){
      this.joinSessionForm.get("pin")?.setValue(this.route.snapshot.paramMap.get("pin"));
    }
  }

  onAuthorizeSpotify(): void {
    this.disableSendButton = true;
    this.authService.authUser().subscribe(
      (response: String) => {
        this.disableSendButton = false;
        this.cookieService.delete("token");
        this.document.location.href = response.toString();
      },
      (error: HttpErrorResponse) => {
        this.disableSendButton = false;
      }
    );
  }

  onAuthorizeGuest(): void {
    this.disableSendButton = true;
    this.errorMessage = undefined;
    let form = this.joinSessionForm.value;
    this.authService.authGuest({
      nickname: form.nickname!,
      invitation_code: form.pin!
    }).subscribe(
      (response: GetSessionDTO) => {
        this.router.navigateByUrl("/session/" + response.id);
        this.disableSendButton = false;
      },
      (error: HttpErrorResponse) => {
        this.disableSendButton = false;
        if(error.status == 400){
          this.errorMessage = error.message;
        }
      }
    );
  }

  get pin() {
    return this.joinSessionForm.get('pin');
  }

}
