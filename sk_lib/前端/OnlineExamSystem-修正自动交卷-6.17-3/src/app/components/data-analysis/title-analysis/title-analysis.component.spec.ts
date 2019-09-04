import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TitleAnalysisComponent } from './title-analysis.component';

describe('TitleAnalysisComponent', () => {
  let component: TitleAnalysisComponent;
  let fixture: ComponentFixture<TitleAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TitleAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TitleAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
