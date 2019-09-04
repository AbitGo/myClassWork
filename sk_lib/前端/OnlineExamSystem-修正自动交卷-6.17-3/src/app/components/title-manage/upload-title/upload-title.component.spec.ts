import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadTitleComponent } from './upload-title.component';

describe('UploadTitleComponent', () => {
  let component: UploadTitleComponent;
  let fixture: ComponentFixture<UploadTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
