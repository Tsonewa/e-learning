import { Component, OnInit} from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/core/service';
import { ICourseEdit } from '../interfaces/ICourseEdit';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.scss']
})
export class EditCourseComponent implements OnInit {

  editCourseForm: FormGroup;
  fileName: string;
  uploadedPicture: FormControl;
  isEditLecturesBtnClicked = false;

  courseToEdit: ICourseEdit;

  constructor(private fb: FormBuilder,
    private courseService: CourseService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {

    this.route.params.subscribe(data => {
      let id = data['id'];

      this.courseService.getCourseById(id).subscribe((course) => {
        this.courseToEdit = course;
      });
    });

    this.editCourseForm = this.fb.group({
      'name': ['', [Validators.required, Validators.minLength(4)]],
      'price': ['', [Validators.required, Validators.min(0)]],
      'description': ['', [Validators.required, Validators.minLength(4)]],
      'videoUrl': ['', [Validators.required, Validators.minLength(4)]],
      imageUrl: ['', [Validators.required]],
      'startDate': ['', [Validators.required]],
      'endDate': ['', [Validators.required]],
      'duration': ['', [Validators.required, Validators.min(0)]],
      'lector': ['', [Validators.required, Validators.minLength(2)]],
      'lectorDescription': ['', [Validators.required, Validators.minLength(4)]],
      'skills': ['', [Validators.required, Validators.minLength(4)]],
      'categories': ['', [Validators.required]],
      'languages': ['', [Validators.required]],
      'lectures': this.fb.array([])

    });

  }

  initLectures() {
    for (const existLecture of this.courseToEdit.lectures) {
      const lecture = this.fb.group({
        'id': [existLecture.id, [Validators.required]],
        'lectureName': ['', [Validators.required, Validators.minLength(2)]],
        'resourceUrl': ['', [Validators.required, Validators.minLength(4)]],
        'lectureDescription': ['', [Validators.required, Validators.minLength(4)]],
      });
      this.lectures.push(lecture);
    }

    this.isEditLecturesBtnClicked = true;
  }


  onPictureUpload(event): void {

    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.editCourseForm.patchValue({
        imageUrl: file
      });
    }
  }

  addLecture() {

    const lecture = this.fb.group({
      'lectureName': ['', [Validators.required, Validators.minLength(2)]],
      'resourceUrl': ['', [Validators.required, Validators.minLength(4)]],
      'lectureDescription': ['', [Validators.required, Validators.minLength(4)]],
    });

    this.lectures.push(lecture);
  }

  get lectures(): FormArray {
    return this.editCourseForm.controls['lectures'] as FormArray;
  }

  editCourse(): any {

    const payload = new FormData();

    payload.append('imageUrl', this.editCourseForm.get('imageUrl').value);

    if (!this.isEditLecturesBtnClicked) {
      this.editCourseForm.value.lectures.push(...this.courseToEdit.lectures);
    }


    this.courseService.editCourse(this.editCourseForm.value, payload, this.route.snapshot.params['id']);

    setTimeout(() =>
      this.router.navigate(['/admin/all']), 1000
    )
  }

  get c(): any {
    return this.editCourseForm.controls;
  }

  removeLecture(index: number) {
    this.lectures.removeAt(index);
  }

}
