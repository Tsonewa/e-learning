import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/service';
import { ICourseDetails } from '../interfaces/ICourseDetails';


@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {
  isEmployee$: Observable<boolean> = this.authService.isEmployee$();

  constructor(@Inject(MAT_DIALOG_DATA) public data: {details: ICourseDetails}, private authService: AuthService) { }

  ngOnInit(): void {
  }
}
