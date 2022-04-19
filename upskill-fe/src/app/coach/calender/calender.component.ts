import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {ISession} from '../../core/model/session-view';
import {CoachService} from '../../core/service/coach.service';
import {ActivatedRoute, Router} from '@angular/router';
import {error} from "protractor";
import {ToastrService} from "ngx-toastr";
import {ATTENTION, ERROR_MESSAGE} from "../constants/constants";

@Component({
  selector: 'app-calender',
  templateUrl: './calender.component.html',
  styleUrls: ['./calender.component.scss']
})
export class CalenderComponent implements OnInit {

  session: Array<ISession>;
  idCoach: string;
  @Output() onChange = new EventEmitter<any>();
  error: null;

  constructor(private coachService: CoachService, private route: ActivatedRoute, private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(data => {
      this.idCoach = data.id;
      this.session = this.coachService.getAllCoachSession(this.idCoach).subscribe((res) => {
        this.session = res;
      }, err => {
        this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
      });
    }, err => {
      this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
    });
  }

  bookSession(id, $event): void {
    this.coachService.bookCoachSession(this.idCoach, id).subscribe((data) => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/coach/employee-profile']);
    }, err => {
      this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
    });
  }
}
