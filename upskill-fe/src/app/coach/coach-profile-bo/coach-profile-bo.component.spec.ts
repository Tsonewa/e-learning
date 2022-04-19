import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachProfileBOComponent } from './coach-profile-bo.component';

describe('CoachProfileBOComponent', () => {
  let component: CoachProfileBOComponent;
  let fixture: ComponentFixture<CoachProfileBOComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachProfileBOComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachProfileBOComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
