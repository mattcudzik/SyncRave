import { Component, Input } from '@angular/core';
import { SearchService } from 'src/api/search.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { TrackObjectDTO } from 'src/model/trackObjectDTO';
import { SessionService } from 'src/api/session.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { MatDialog } from '@angular/material/dialog';
import { PopupInfoComponent, PopupInfoContent } from '../popup-info/popup-info.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  @Input() sessionId = "";

  results: TrackObjectDTO[] = [];
  page: number = 0;
  limit: number = 20;
  waitningForSearchResponse = false;
  disableAddButtons = false;

  searchForm = this.formBuilder.group(
    {
      query: ""
    }
  )

  constructor(
    private searchService: SearchService,
    private formBuilder: FormBuilder,
    private cookieService : CookieService,
    private sessionService: SessionService,
    private dialog: MatDialog
    ){
      let token = this.cookieService.get('token');
      this.searchService.configuration.withCredentials = true;
      this.searchService.configuration.credentials['bearerAuth'] = token;
      
      this.sessionService.configuration.withCredentials = true;
      this.sessionService.configuration.credentials['bearerAuth'] = token;
    }

  onSearch(): void {
    var form = this.searchForm.value;
    this.waitningForSearchResponse = true;

    this.searchService.searchSpotify(form.query!, this.limit, 0).subscribe(
      (response: TrackObjectDTO[]) => {
        this.waitningForSearchResponse = false;
        this.results = response;
      }
    );
  }

  onNextPage() : void{
    var form = this.searchForm.value;
    this.page++;

    if(this.page * this.limit > 1000) {
      this.page--;
    }
    else {
      this.searchService.searchSpotify(form.query!, this.limit, this.limit * this.page).subscribe(
        (response: TrackObjectDTO[]) => {
          this.results = response;
        }
      );
    }

    
  }

  onPreviousPage(): void{
    var form = this.searchForm.value;
    this.page--;

    if(this.page < 0){
      
      this.page = 0;
    }
    else {

      this.searchService.searchSpotify(form.query!, this.limit, this.limit * this.page).subscribe(
        (response: TrackObjectDTO[]) => {
          this.results = response;
        }
      );
    }
  
  }

  onAddSong(track: TrackObjectDTO): void {
    this.disableAddButtons = true;
    this.sessionService.addSongToQueue(this.sessionId,{id: track.id}).subscribe(
      (okResponse : any) => {
        this.openInfoDialog(track.name);
        this.disableAddButtons = false;
      },
      (error : HttpErrorResponse) => {
        this.openErrorDialog(error.message);
        this.disableAddButtons = false;
        console.log(error);
      }
    );
  }

  openInfoDialog(content : string) : void {
    this.dialog.open(PopupInfoComponent, {
      data: new PopupInfoContent(content, "Song added to queue succesfully!")
    })
  }

  openErrorDialog(content : string ) {
    this.dialog.open(PopupInfoComponent, {
      data: new PopupInfoContent(content, "Oops something went wrong...")
    })
  }
}
