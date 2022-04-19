import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {IEmployeeCourse} from 'src/app/core/model/employee-course';
import {AuthService, CourseService} from 'src/app/core/service';
import {CoachService} from 'src/app/core/service/coach.service';
import {ICoachesCreate} from '../../core/model/coaches-create';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.scss']
})
export class EmployeeDashboardComponent implements OnInit {

  randomCourses$: Observable<Array<IEmployeeCourse>>;
  randomCoaches$: Observable<Array<ICoachesCreate>>;
  boEmail;

  constructor(private coachService: CoachService, private courseService: CourseService) {
  }

  ngOnInit(): void {

    this.randomCourses$ = this.courseService.getEmployeeRandomCourses();
    this.coachService.getCompanyOwnerEmail().subscribe(mail => {
      this.boEmail = mail;
      this.randomCoaches$ = this.coachService.getEmployeeRandomCoaches(this.boEmail);
    }, err => {
      console.log(err);
    });
  }
}
