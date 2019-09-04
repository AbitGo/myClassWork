import { TestBed, inject } from '@angular/core/testing';

import { TeacherManageServerService } from './teacher-manage-server.service';

describe('TeacherManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TeacherManageServerService]
    });
  });

  it('should be created', inject([TeacherManageServerService], (service: TeacherManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
