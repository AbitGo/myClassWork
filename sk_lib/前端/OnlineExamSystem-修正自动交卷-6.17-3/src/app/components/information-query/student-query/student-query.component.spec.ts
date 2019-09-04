import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentQueryComponent } from './student-query.component';

describe('StudentQueryComponent', () => {
  let component: StudentQueryComponent;
  let fixture: ComponentFixture<StudentQueryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentQueryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
