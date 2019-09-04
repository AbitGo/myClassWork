import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MajorManageComponent } from './major-manage.component';

describe('MajorManageComponent', () => {
  let component: MajorManageComponent;
  let fixture: ComponentFixture<MajorManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MajorManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MajorManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
