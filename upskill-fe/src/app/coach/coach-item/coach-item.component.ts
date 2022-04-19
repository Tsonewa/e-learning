import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CoachService} from '../../core/service/coach.service';
import {AuthService} from '../../core/service';
import {ICoaches} from '../../core/model/coaches-view';
import {ICoachesDetails} from '../../core/model/coaches-details';
import {Observable} from 'rxjs';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {MatDialog} from '@angular/material/dialog';
import {DialogElementsComponent} from './dialog-elements/dialog-elements.component';
import {CoachDetailsComponent} from '../coach-details/coach-details.component';
import {ToastrService} from 'ngx-toastr';
import {ADMIN_COACH_REMOVED_MESSAGE, ATTENTION, BO_COACH_ADDED_MESSAGE, ERROR_MESSAGE} from "../constants/constants";


@Component({
  selector: 'app-coach-item',
  templateUrl: './coach-item.component.html',
  styleUrls: ['./coach-item.component.scss']
})
export class CoachItemComponent implements OnInit {


  @Input('coach')
  coach: ICoaches;
  isBusinessOwner$: Observable<boolean> = this.authService.isBusinessOwner$();
  isAdmin$: Observable<boolean> = this.authService.isAdmin$();
  currentId: string;
  @Output() onChange = new EventEmitter<any>();
  interval;
  coachDetails: ICoachesDetails;
  isRejected = false;

  constructor(
    private coachService: CoachService,
    private authService: AuthService,
    private dialog: MatDialog,
    private dialogService: DialogElementsComponent,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService
  ) {
  }


  ngOnInit(): void {
  }

  addCoachToBo(id: string, $event): void {

    this.coachService.addCoachToBo(id)
      .subscribe((data) => {
        this.onChange.emit($event);
      });
    this.toastr.success(BO_COACH_ADDED_MESSAGE);
  }

  openMaterialModal(id: string, $event): void {
    this.dialogService
      .openDialog()
      .afterClosed()
      .subscribe((response) => {
        if (response) {
          this.coachService.deleteCoachByAdmin(id)
            .subscribe((data) => {
              if (data) {
                this.onChange.emit($event);
                this.toastr.success(ADMIN_COACH_REMOVED_MESSAGE);
              } else {
                this.isRejected = true;
                setTimeout(() => this.isRejected = false, 5000);
              }
            }, error => {
              this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
            });
        }
      });
  }


  onDetailsDialog(id: string): void {
    this.coachService.getCoachDetails(id)
      .subscribe((data) => {
        this.coachDetails = data;
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
    this.interval = setTimeout(() => {
      let dialogRef = this.dialog.open(CoachDetailsComponent, {
        panelClass: 'custom-mat-dialog-container',
        data: {
          details: {
            topic: this.coachDetails?.topic,
            picture: this.coachDetails?.picture,
            name: this.coachDetails?.name,
            company: this.coachDetails?.company,
            description: this.coachDetails?.description,
            goals: this.coachDetails.goals?.split(', '),
            safeSrc: this.sanitizer.bypassSecurityTrustResourceUrl(this.coachDetails?.introductionVideo),
            duration: this.coachDetails?.duration,
            resource: this.coachDetails?.resource,
          },
        },
      });
    }, 500);
  }

}
