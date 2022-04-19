import {Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import ICourseItem from '../interfaces/ICourseItem';
import {CourseService, AuthService} from '../../core/service';
import {DialogElementsComponent} from './dialog-elements/dialog-elements.component';
import {MatDialog} from '@angular/material/dialog';
import {CourseDetailsComponent} from '../course-details/course-details.component';
import {ICourseDetails} from 'src/app/course/interfaces/ICourseDetails';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {IEmployeeCourse} from '../../core/model';
import {take} from 'rxjs/operators';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-course-item',
  templateUrl: './course-item.component.html',
  styleUrls: ['./course-item.component.scss'],
})
export class CourseItemComponent implements OnInit {
  isBusinessOwner$: Observable<boolean> = this.authService.isBusinessOwner$();
  isEmployee$: Observable<boolean> = this.authService.isEmployee$();
  isAdmin$: Observable<boolean> = this.authService.isAdmin$();

  @Input('employeeCourse')
  employeeCourse: IEmployeeCourse;

  @Input('course')
  course: ICourseItem;

  courses: Array<ICourseItem> = [];

  @Output('courseDetails')
  courseDetails: ICourseDetails;

  constructor(
    private courseService: CourseService,
    private dialogService: DialogElementsComponent,
    private router: Router,
    private dialog: MatDialog,
    private authService: AuthService,
    private toastr: ToastrService
  ) {
  }

  ngOnInit(): void {
  }

  onAddCourse(id: string) {
    this.courseService.addCourseToBusinessOwner(id).subscribe();
    this.reloadComponent();
  }

  onRemoveCourse(id: string) {
    this.courseService.removeCourseFromBusinessOwnerList(id).subscribe();
    this.reloadComponent();
  }

  onDeleteCourse(id: string) {
    this.dialogService
      .openDialog()
      .afterClosed()
      .subscribe((response) => {
        if (response) {

          this.courseService.deleteCourse(id).subscribe((course) => {

            if (course) {
              this.toastr.success('The course has been deleted successfully!', 'Delete course');
            } else {
              console.log('Delete forbidden!');
            }
            this.reloadComponent();
          });
        }
      });
  }

  reloadComponent() {
    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }

  onDetailsDialog() {
    this.courseService
      .getCourseDetailsById(this.course.courseId)
      .subscribe((data) => {
        this.courseDetails = data;
        let dialogRef = this.dialog.open(CourseDetailsComponent, {
          panelClass: 'custom-mat-dialog-container',
          hasBackdrop: true,
          data: {
            details: {
              name: this.courseDetails.name,
              lector: this.courseDetails.lector,
              description: this.courseDetails.description,
              skills: this.courseDetails.skills.replace('s+', '').split(','),
              duration: this.courseDetails.duration,
              lecturesCount: this.courseDetails.lecturesCount,
            },
          },
        });
      });


  }

  changeStatus(courseId: string) {
    this.courseService
      .changeEmployeeCourseStatus(courseId)
      .pipe(take(1))
      .subscribe();
    this.reloadComponent();
  }
}
