import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CoachService} from '../../core/service/coach.service';
import {AuthService} from '../../core/service';
import {ICoachesCreate} from '../../core/model/coaches-create';
import {ICoachesDetails} from '../../core/model/coaches-details';
import {DatePipe} from '@angular/common';
import {Observable} from 'rxjs';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {MatDialog} from '@angular/material/dialog';
import {CoachDetailsComponent} from '../coach-details/coach-details.component';
import {ToastrService} from 'ngx-toastr';
import {ATTENTION, BO_COACH_ADDED_MESSAGE, BO_COACH_REMOVED_MESSAGE, ERROR_MESSAGE} from '../constants/constants';


@Component({
  selector: 'app-coach-item-bo',
  templateUrl: './coach-item-bo.component.html',
  styleUrls: ['./coach-item-bo.component.scss']
})
export class CoachItemBoComponent implements OnInit {

  interval;
  @Input('coach')
  coach: ICoachesCreate;
  isBusinessOwner$: Observable<boolean> = this.authService.isBusinessOwner$();
  isEmployee$: Observable<boolean> = this.authService.isEmployee$();
  currentId: string;
  displayStyle = 'none';
  @Output() onChange = new EventEmitter<any>();
  coaches: Array<ICoachesCreate> = [];
  coachDetails: ICoachesDetails;
  goals: string[] = [];
  now: Date = new Date();
  endDate;
  safeSrc: SafeResourceUrl;

  constructor(private coachService: CoachService, private authService: AuthService,
              public datepipe: DatePipe, private sanitizer: DomSanitizer, private dialog: MatDialog,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.endDate = new Date(this.coach.endDate);
  }

  removeCoach(id: string, $event): any {
    this.coachService.deleteCoachByBo(id).subscribe((data) => {
      this.onChange.emit($event);

      this.toastr.success(BO_COACH_REMOVED_MESSAGE);
    }, error => {
      this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
    });
  }

  onDetailsDialog(id: string): void {
    this.coachService.getCoachDetails(id)
      .subscribe(data => {
        this.coachDetails = data;
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
    this.interval = setTimeout(() => {
      let dialogRef = this.dialog.open(CoachDetailsComponent, {
        panelClass: 'custom-mat-dialog-container',
        hasBackdrop: true,
        disableClose: true,
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
            id: this.coachDetails?.id,
            owned: this.coach.owned,
          },
        },
      });
    }, 500);
  }

  bookSession($event): void {
    this.coachService.bookCoach(this.currentId).subscribe((data) => {
      this.onChange.emit($event);

      this.toastr.success(BO_COACH_ADDED_MESSAGE);
    }, (error) => {
      this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
    });
  }

}
