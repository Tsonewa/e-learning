import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs/operators';
import { CourseService } from 'src/app/core/service';
import { ILectureItem } from '../interfaces/ILectureItem';
import IStreamCourse from '../interfaces/IStreamCourse';

@Component({
  selector: 'app-course-stream',
  templateUrl: './course-stream.component.html',
  styleUrls: ['./course-stream.component.scss']
})
export class CourseStreamComponent implements OnInit {

  streamCourse: IStreamCourse;
  currentLecture!: ILectureItem;
  trustedUrl: any;

  constructor(private courseService: CourseService, private route: ActivatedRoute, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {

    this.route.params.subscribe(data => {
      let id = data['id'];

      this.courseService.getStreamCoursebyId(id).subscribe((course) => {
        this.streamCourse = course;
      });

      this.changeStatus(id);
    });
  }

  onClick(lecture: any) {
    this.currentLecture = lecture;
    this.trustedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.currentLecture.resourceUrl);
  }

  changeStatus(courseId: string) {
    this.courseService
      .changeEmployeeCourseStatus(courseId)
      .pipe(take(1))
      .subscribe();
  }

}
