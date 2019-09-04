import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamPageComponent } from './exam-page.component';

describe('ExamPageComponent', () => {
  let component: ExamPageComponent;
  let fixture: ComponentFixture<ExamPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
