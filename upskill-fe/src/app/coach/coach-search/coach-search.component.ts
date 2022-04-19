import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ICoaches} from '../../core/model/coaches-view';
import {CoachService} from '../../core/service/coach.service';
import {Router} from '@angular/router';
import {AuthService} from '../../core/service';
import {ICoachesCreate} from '../../core/model/coaches-create';
import {Observable} from "rxjs";
import {ICoachesDetails} from '../../core/model/coaches-details';
import {MatDialog} from '@angular/material/dialog';
import {CoachDetailsComponent} from '../coach-details/coach-details.component';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {ToastrService} from "ngx-toastr";
import {
  ADMIN_COACH_REMOVED_MESSAGE,
  ATTENTION,
  BO_COACH_ADDED_MESSAGE,
  BO_COACH_REMOVED_MESSAGE,
  ERROR_MESSAGE
} from "../constants/constants";

@Component({
  selector: 'app-coach-search',
  templateUrl: './coach-search.component.html',
  styleUrls: ['./coach-search.component.scss']
})
export class CoachSearchComponent implements OnInit {
  @Input('coach')
  coach: ICoachesCreate;
  coaches: Array<ICoaches> = [];
  isBusinessOwner$: Observable<boolean> = this.authService.isBusinessOwner$();
  isAdmin$: Observable<boolean> = this.authService.isAdmin$();
  @Output() onChange = new EventEmitter<any>();
  interval;
  coachDetails: ICoachesDetails;

  constructor(private coachService: CoachService, private router: Router,
              private authService: AuthService, private dialog: MatDialog,
              private sanitizer: DomSanitizer, private toastr: ToastrService) {
  }

  ngOnInit(): void {
  }

  removeCoach(id: string, $event): any {
    this.coachService.deleteCoachByBo(id)
      .subscribe((data) => {
        this.toastr.success(BO_COACH_REMOVED_MESSAGE);
        this.onChange.emit($event);
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
  }

  addCoachToBo(id: string, $event): void {
    this.coachService.addCoachToBo(id)
      .subscribe((data) => {
        this.toastr.success(BO_COACH_ADDED_MESSAGE);
        this.onChange.emit($event);
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
  }

  deleteCoach(id: string, $event): any {
    this.coachService.deleteCoachByAdmin(id)
      .subscribe((data) => {
        this.toastr.success(ADMIN_COACH_REMOVED_MESSAGE);
        this.onChange.emit($event);
      }, error => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
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
