import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CoachService} from '../../core/service/coach.service';
import {ICoachesEdit} from '../../core/model/coach-edit';
import {ActivatedRoute, Router} from '@angular/router';
import {ATTENTION, ERROR_MESSAGE} from "../constants/constants";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-coach-feedback',
  templateUrl: './coach-feedback.component.html',
  styleUrls: ['./coach-feedback.component.scss']
})
export class CoachFeedbackComponent implements OnInit {

  contactForm: FormGroup;
  id: string;

  ngOnInit(): void {
    this.route.params.subscribe(data => {
      this.id = data.id;
    });
    this.contactForm = this.fb.group({
      subjects: ['', [Validators.required]],
      message: ['', [Validators.required, Validators.maxLength(2000)]],
    });
  }

  constructor(private fb: FormBuilder, private coachService: CoachService,
              private route: ActivatedRoute, private router: Router,
              private toastr: ToastrService) {
  }

  sendMessage(): any {
    this.coachService.sendFeedback(this.id, this.contactForm.value).subscribe((data) => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigate(['/coach/employee-profile']);
    }, error => {
      this.toastr.error(ERROR_MESSAGE, ATTENTION, {timeOut: 5000000});
    });
  }
}

