import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowledageManageComponent } from './knowledage-manage.component';

describe('KnowledageManageComponent', () => {
  let component: KnowledageManageComponent;
  let fixture: ComponentFixture<KnowledageManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KnowledageManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KnowledageManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
