import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeadershipManageComponent } from './leadership-manage.component';

describe('LeadershipManageComponent', () => {
  let component: LeadershipManageComponent;
  let fixture: ComponentFixture<LeadershipManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeadershipManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeadershipManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
