import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectBindComponent } from './subject-bind.component';

describe('SubjectBindComponent', () => {
  let component: SubjectBindComponent;
  let fixture: ComponentFixture<SubjectBindComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubjectBindComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectBindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
