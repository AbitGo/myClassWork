import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaperHistoryComponent } from './paper-history.component';

describe('PaperHistoryComponent', () => {
  let component: PaperHistoryComponent;
  let fixture: ComponentFixture<PaperHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaperHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
