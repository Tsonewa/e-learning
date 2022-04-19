import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachSearchComponent } from './coach-search.component';

describe('CoachSearchComponent', () => {
  let component: CoachSearchComponent;
  let fixture: ComponentFixture<CoachSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
