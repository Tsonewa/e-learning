import {Component, Input, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {FormBuilder, Validators, FormGroup, FormControl, FormArray} from '@angular/forms';
import {ICoaches} from '../../core/model/coaches-view';
import {CoachService} from '../../core/service/coach.service';



@Component({
  selector: 'app-coach-all',
  templateUrl: './coach-all.component.html',
  styleUrls: ['./coach-all.component.scss']
})
export class CoachAllComponent implements OnInit {


  loading = true;
  interval;
  form: FormGroup;
  coaches$: Observable<Array<ICoaches>>;
  fileName: File;
  uploadedPicture: FormControl;
  divs: number[] = [];
  languages = [
    {language: 'ENGLISH'},
    {language: 'SPANISH'},
    {language: 'GERMAN'},
    {language: 'FRENCH'},
  ];
  categories = [
    {category: 'ART'},
    {category: 'DESIGN'},
    {category: 'MARKETING'},
    {category: 'LEADERSHIP'},
    {category: 'PERSONAL_DEVELOPMENT'},
    {category: 'DATA_SCIENCE'},
    {category: 'COMPUTER_SCIENCE'},
  ];

  constructor(
    private coachService: CoachService,
    private fb: FormBuilder,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    if (this.loading) {
      this.interval = setTimeout(() => {
        this.coaches$ = this.coachService.getAllCoachesByAdmin();
        this.loading = false;
      }, 2000);
    }


    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      email: ['', [Validators.required, Validators.email]],
      company: ['', [Validators.required, Validators.minLength(4)]],
      picture: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.min(0)]],
      topic: ['', [Validators.required]],
      categories: [this.categories[0], [Validators.required]],
      languages: [this.languages[0], [Validators.required]],
      startDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      duration: ['', [Validators.required]],
      introductionVideo: ['', [Validators.required]],
      goals: ['', [Validators.required, Validators.minLength(4)]],
      // resource: [this.resources.get('resources') as FormArray,  [Validators.required]],
      resources: this.fb.array([]),
    });
  }

  onFileSelected(event): void {
    // const file: File = event.target.files[0];
    // console.log(file);
    // if (file) {
    //   // this.uploadedPicture = this.fb.control(file);
    //   this.fileName = file;
    //   this.form.get('picture').setValue(file);
    // }
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.form.patchValue({
        picture: file
      });
    }
  }


  createCoach(): any {
    const payload = new FormData();
    payload.append('picture', this.form.get('picture').value);
    // payload.append('email', this.form.get('email').value);
    // payload.append('name', this.form.get('name').value);
    // payload.append('company', this.form.get('company').value);
    // payload.append('price', this.form.get('price').value);
    // payload.append('topic', this.form.get('topic').value);
    // payload.append('categories', this.form.get('categories').value);
    // payload.append('languages', this.form.get('languages').value);
    // payload.append('startDate', this.form.get('startDate').value);
    // payload.append('endDate', this.form.get('endDate').value);
    // payload.append('description', this.form.get('description').value);
    // payload.append('duration', this.form.get('duration').value);
    // payload.append('introductionVideo', this.form.get('introductionVideo').value);
    // payload.append('goals', this.form.get('goals').value);
    // payload.append('resources', this.form.get('resources').value);


    this.coachService.createCoach(this.form.value, payload);
    this.interval = setTimeout(() => {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/coach/all-admin']);
    }, 2000);
  }

  get c(): any {
    return this.form.controls;
  }

  createDiv(): void {
    const resource = this.fb.group({
      title: ['', [Validators.required]],
      filePath: ['', [Validators.required]],
    });
    this.resources.push(resource);
  }

  removeDiv(id: number): void {
    this.resources.removeAt(id);
  }

  get resources(): FormArray {
    return this.form.controls.resources as FormArray;
  }

  reloadChange($event): void {
    this.coaches$ = this.coachService.getAllCoachesByAdmin();
  }

  get invalid(): boolean {
    return this.form.invalid;
  }

}
