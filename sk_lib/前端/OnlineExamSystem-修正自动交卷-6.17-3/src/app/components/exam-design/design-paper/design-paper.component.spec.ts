import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignPaperComponent } from './design-paper.component';

describe('DesignPaperComponent', () => {
  let component: DesignPaperComponent;
  let fixture: ComponentFixture<DesignPaperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DesignPaperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DesignPaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
