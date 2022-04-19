import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {ICoaches} from '../../core/model/coaches-view';
import {CoachService} from '../../core/service/coach.service';
import {ICoachesCreate} from '../../core/model/coaches-create';

@Component({
  selector: 'app-coach-profile-bo',
  templateUrl: './coach-profile-bo.component.html',
  styleUrls: ['./coach-profile-bo.component.scss']
})
export class CoachProfileBOComponent implements OnInit {
  boCoaches$: Observable<Array<ICoachesCreate>>;
  constructor(private coachService: CoachService) { }

  ngOnInit(): void {
    this.boCoaches$ = this.coachService.getAllCoachesByBo();
  }


  reloadChange($event): void {
    this.boCoaches$ = this.coachService.getAllCoachesByBo();
  }

}
