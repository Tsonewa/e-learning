import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachEditComponent } from './coach-edit.component';

describe('CoachEditComponent', () => {
  let component: CoachEditComponent;
  let fixture: ComponentFixture<CoachEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
