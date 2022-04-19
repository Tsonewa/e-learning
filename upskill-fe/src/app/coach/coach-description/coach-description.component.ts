import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {CoachService} from '../../core/service/coach.service';
import {ICoachesCreate} from '../../core/model/coaches-create';
import {ToastrService} from "ngx-toastr";
import {ATTENTION, ERROR_MESSAGE} from "../constants/constants";

@Component({
  selector: 'app-coach-description',
  templateUrl: './coach-description.component.html',
  styleUrls: ['./coach-description.component.scss']
})
export class CoachDescriptionComponent implements OnInit {

  emplCoaches$: Observable<Array<ICoachesCreate>>;
  loading = true;
  interval;
  email: string;
  error: null;


  constructor(private coachService: CoachService, private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.coachService.getCompanyOwnerEmail().subscribe(res => {
      this.email = res;
    }, error => {
      console.log(error);
      // Here want to pass the error message from back-end

      this.toastr.error(ERROR_MESSAGE, ATTENTION, {
        timeOut: 5000000
      });
    });

    if (this.loading) {
      this.interval = setTimeout(() => {
        this.loading = false;
        this.emplCoaches$ = this.coachService.getAllCoachesByEmployee(this.email);
      }, 2000);
    }
  }

  reloadChange($event): void {
    this.emplCoaches$ = this.coachService.getAllCoachesByEmployee(this.email);
  }
}
