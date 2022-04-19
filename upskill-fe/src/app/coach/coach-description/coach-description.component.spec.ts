import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachDescriptionComponent } from './coach-description.component';

describe('CoachDescriptionComponent', () => {
  let component: CoachDescriptionComponent;
  let fixture: ComponentFixture<CoachDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachDescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
