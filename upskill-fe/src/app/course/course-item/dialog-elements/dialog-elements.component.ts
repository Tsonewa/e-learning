import {Component} from '@angular/core';
import {DeleteDialogComponent} from '../delete-dialog/delete-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {RemoveDialogComponent} from '../remove-dialog/remove-dialog.component';

@Component({
  selector: 'app-dialog-elements',
  templateUrl: './dialog-elements.component.html',
  styleUrls: ['./dialog-elements.component.scss']
})
export class DialogElementsComponent {

  constructor(public dialog: MatDialog) {
  }

  openDialog() {
    return this.dialog.open(DeleteDialogComponent, {hasBackdrop: true});
  }

  openRemoveDialog() {
    return this.dialog.open(RemoveDialogComponent, {hasBackdrop: true});
  }
}
