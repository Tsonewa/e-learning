import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAllComponent } from './admin-all.component';

describe('AdminAllComponent', () => {
  let component: AdminAllComponent;
  let fixture: ComponentFixture<AdminAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAllComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
