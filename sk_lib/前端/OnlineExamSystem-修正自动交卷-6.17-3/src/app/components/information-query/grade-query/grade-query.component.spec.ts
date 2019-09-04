import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GradeQueryComponent } from './grade-query.component';

describe('GradeQueryComponent', () => {
  let component: GradeQueryComponent;
  let fixture: ComponentFixture<GradeQueryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GradeQueryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GradeQueryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
