import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-popup-info',
  template: `
  <h1 mat-dialog-title>{{ data.title }}</h1>
  <mat-dialog-content>{{ data.message }}</mat-dialog-content>
  <mat-dialog-actions>
    <button mat-button mat-dialog-close>OK</button>
  </mat-dialog-actions>
  `,
  styleUrls: ['./popup-info.component.css']
})
export class PopupInfoComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: PopupInfoContent) { }
}

export class PopupInfoContent {
  constructor(public message : string, public title : string) {}
}
