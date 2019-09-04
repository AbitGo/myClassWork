import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowledgeAnalysisComponent } from './knowledge-analysis.component';

describe('KnowledgeAnalysisComponent', () => {
  let component: KnowledgeAnalysisComponent;
  let fixture: ComponentFixture<KnowledgeAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KnowledgeAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KnowledgeAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
