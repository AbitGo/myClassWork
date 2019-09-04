import { TestBed, inject } from '@angular/core/testing';

import { DepartmentManageServerService } from './department-manage-server.service';

describe('DepartmentManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DepartmentManageServerService]
    });
  });

  it('should be created', inject([DepartmentManageServerService], (service: DepartmentManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
