import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanJobComponent } from './plan-job.component';

describe('PlanJobComponent', () => {
  let component: PlanJobComponent;
  let fixture: ComponentFixture<PlanJobComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlanJobComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
