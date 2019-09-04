import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherManageComponent } from './teacher-manage.component';

describe('TeacherManageComponent', () => {
  let component: TeacherManageComponent;
  let fixture: ComponentFixture<TeacherManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeacherManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
