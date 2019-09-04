import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherQueryComponent } from './teacher-query.component';

describe('TeacherQueryComponent', () => {
  let component: TeacherQueryComponent;
  let fixture: ComponentFixture<TeacherQueryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherQueryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
