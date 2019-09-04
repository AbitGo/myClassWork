import { TestBed, inject } from '@angular/core/testing';

import { ClassManageServerService } from './class-manage-server.service';

describe('ClassManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClassManageServerService]
    });
  });

  it('should be created', inject([ClassManageServerService], (service: ClassManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
