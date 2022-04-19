import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CourseService } from 'src/app/core/service/course.service';
import { Router } from '@angular/router';
import {ToastrService} from "ngx-toastr";


@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.scss']
})
export class CreateCourseComponent implements OnInit {

  createCourseForm: FormGroup;
  fileName: string;
  uploadedPicture: FormControl;

  constructor(private fb: FormBuilder, private courseService: CourseService,
              private toastr: ToastrService, 
              private router:Router) {
  }

  ngOnInit(): void {

    this.createCourseForm = this.fb.group({
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
    this.addLecture();
  }

  onPictureUpload(event) {

    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.createCourseForm.patchValue({
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
    return this.createCourseForm.controls['lectures'] as FormArray;
  }

  onCreateCourse() {
    const payload = new FormData();
    payload.append('imageUrl', this.createCourseForm.get('imageUrl').value);

    this.courseService.createCourse(this.createCourseForm.value, payload);
    this.createCourseForm.reset();
    setTimeout(() =>
      this.router.navigate(['/admin/all']), 1000
    )
  }

  get c(): any {
    return this.createCourseForm.controls;
  }

  removeLecture(index: number) {
    this.lectures.removeAt(index);
  }

  reloadComponent() {
    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }
}
