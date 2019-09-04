import { TestBed, inject } from '@angular/core/testing';

import { SchoolManageServerService } from './school-manage-server.service';

describe('SchoolManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SchoolManageServerService]
    });
  });

  it('should be created', inject([SchoolManageServerService], (service: SchoolManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
