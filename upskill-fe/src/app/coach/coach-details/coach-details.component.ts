import {Component, EventEmitter, OnInit, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {CoachService} from '../../core/service/coach.service';
import {AuthService} from '../../core/service';
import {Observable} from 'rxjs';

import {Router} from '@angular/router';

@Component({
  selector: 'app-coach-details',
  templateUrl: './coach-details.component.html',
  styleUrls: ['./coach-details.component.scss']
})
export class CoachDetailsComponent implements OnInit {

  isEmployee$: Observable<boolean> = this.authService.isEmployee$();
  @Output() onChange = new EventEmitter<any>();

  constructor(private coachService: CoachService, private authService: AuthService,
              @Inject(MAT_DIALOG_DATA) public data: { details: any, hasBackdrop: true, disableClose: true }) {
  }

  ngOnInit(): void {
  }

  bookSession(id: string, $event): any {
    this.coachService.bookCoach(id).subscribe((data) => {
      this.onChange.emit($event);
    });
  }

}
