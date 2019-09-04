import { TestBed, inject } from '@angular/core/testing';

import { StudentManageServerService } from './student-manage-server.service';

describe('StudentManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StudentManageServerService]
    });
  });

  it('should be created', inject([StudentManageServerService], (service: StudentManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
