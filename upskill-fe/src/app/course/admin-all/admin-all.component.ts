import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseService } from 'src/app/core/service';
import ICourseItem from '../interfaces/ICourseItem';

@Component({
  selector: 'app-admin-all',
  templateUrl: './admin-all.component.html',
  styleUrls: ['./admin-all.component.scss']
})
export class AdminAllComponent implements OnInit {

  allCourses$: Observable<Array<ICourseItem>>;
  loading = true;
  interval;

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
    if (this.loading) {
      this.interval = setTimeout(() => {
        this.allCourses$ = this.courseService.getAllAdminCourse();
        this.loading = false;
      }, 2000);
  }
  }

  reloadChange($event): void {
    this.allCourses$=this.courseService.getAllAdminCourse();
  }

}
