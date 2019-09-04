import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAnalysisComponent } from './get-analysis.component';

describe('GetAnalysisComponent', () => {
  let component: GetAnalysisComponent;
  let fixture: ComponentFixture<GetAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
