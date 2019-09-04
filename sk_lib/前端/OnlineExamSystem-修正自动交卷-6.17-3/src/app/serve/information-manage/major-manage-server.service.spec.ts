import { TestBed, inject } from '@angular/core/testing';

import { MajorManageServerService } from './major-manage-server.service';

describe('MajorManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MajorManageServerService]
    });
  });

  it('should be created', inject([MajorManageServerService], (service: MajorManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
