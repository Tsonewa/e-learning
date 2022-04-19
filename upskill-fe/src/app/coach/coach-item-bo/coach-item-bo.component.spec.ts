import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachItemBoComponent } from './coach-item-bo.component';

describe('CoachItemBoComponent', () => {
  let component: CoachItemBoComponent;
  let fixture: ComponentFixture<CoachItemBoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachItemBoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoachItemBoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
