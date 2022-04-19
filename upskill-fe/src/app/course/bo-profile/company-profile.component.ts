import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CourseService } from 'src/app/core/service';
import { DialogElementsComponent } from '../course-item/dialog-elements/dialog-elements.component';
import ICourseItem from '../interfaces/ICourseItem';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.scss']
})
export class CompanyProfileComponent implements OnInit {

  businessOwnerCourses$: Observable<Array<ICourseItem>>;

  constructor(private coursesService: CourseService) { }

  ngOnInit(): void {

    this.businessOwnerCourses$ = this.coursesService.getCoursesByBusinessOwner();
    this.businessOwnerCourses$.subscribe((data) => {
      })
  }
}
