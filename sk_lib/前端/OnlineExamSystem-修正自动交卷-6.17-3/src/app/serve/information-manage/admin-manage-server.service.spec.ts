import { TestBed, inject } from '@angular/core/testing';

import { AdminManageServerService } from './admin-manage-server.service';

describe('AdminManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminManageServerService]
    });
  });

  it('should be created', inject([AdminManageServerService], (service: AdminManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
