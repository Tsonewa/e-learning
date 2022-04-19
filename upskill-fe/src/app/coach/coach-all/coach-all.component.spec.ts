import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachAllComponent } from './coach-all.component';

describe('CoachAllComponent', () => {
  let component: CoachAllComponent;
  let fixture: ComponentFixture<CoachAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
