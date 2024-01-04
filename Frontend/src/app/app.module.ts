import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import { AdminSessionsComponent } from './admin-sessions/admin-sessions.component';

import { RouterModule, RouterOutlet, provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { PlayerComponent } from './player/player.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SessionComponent } from './session/session.component';
import { SearchComponent } from './search/search.component';
import { LoadingComponent } from './loading/loading.component';
import { SessionSummaryComponent } from './session-summary/session-summary.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { PopupInfoComponent } from './popup-info/popup-info.component';


@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    AdminSessionsComponent,
    PlayerComponent,
    SessionComponent,
    SearchComponent,
    LoadingComponent,
    SessionSummaryComponent,
    PopupInfoComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    MatDialogModule,
    MatButtonModule
  ],
  providers: [

    CookieService
  ],
  bootstrap: [AppComponent],
  exports: [ RouterModule ]
})
export class AppModule { }
