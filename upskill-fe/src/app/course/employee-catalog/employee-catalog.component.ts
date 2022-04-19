import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseService } from '../../core/service';
import { IEmployeeCoursesList } from '../../core/model';
import { take } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-catalog',
  templateUrl: './employee-catalog.component.html',
  styleUrls: ['./employee-catalog.component.scss'],
})
export class EmployeeCatalogComponent implements OnInit {
  employeeCourses$: Observable<IEmployeeCoursesList>;
  constructor(private courseService: CourseService, private router:Router) {
  }

  ngOnInit(): void {
    this.employeeCourses$ = this.courseService.getCoursesByEmployee();
    this.employeeCourses$.subscribe((data) => {
    });
  }

  emitClick(event:boolean){   
    this.router.navigate(['/employee/catalog']);
  }
}
