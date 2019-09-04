import { TestBed, inject } from '@angular/core/testing';

import { LeadershipManageServerService } from './leadership-manage-server.service';

describe('LeadershipManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LeadershipManageServerService]
    });
  });

  it('should be created', inject([LeadershipManageServerService], (service: LeadershipManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
