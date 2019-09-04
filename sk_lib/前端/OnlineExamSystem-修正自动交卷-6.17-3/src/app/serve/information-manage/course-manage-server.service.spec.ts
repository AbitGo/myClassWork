import { TestBed, inject } from '@angular/core/testing';

import { CourseManageServerService } from './course-manage-server.service';

describe('CourseManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CourseManageServerService]
    });
  });

  it('should be created', inject([CourseManageServerService], (service: CourseManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
