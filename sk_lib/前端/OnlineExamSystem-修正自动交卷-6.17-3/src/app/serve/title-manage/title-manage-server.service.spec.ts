import { TestBed, inject } from '@angular/core/testing';

import { TitleManageServerService } from './title-manage-server.service';

describe('TitleManageServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TitleManageServerService]
    });
  });

  it('should be created', inject([TitleManageServerService], (service: TitleManageServerService) => {
    expect(service).toBeTruthy();
  }));
});
