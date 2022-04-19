import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseStreamComponent } from './course-stream.component';

describe('CourseStreamComponent', () => {
  let component: CourseStreamComponent;
  let fixture: ComponentFixture<CourseStreamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseStreamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseStreamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
