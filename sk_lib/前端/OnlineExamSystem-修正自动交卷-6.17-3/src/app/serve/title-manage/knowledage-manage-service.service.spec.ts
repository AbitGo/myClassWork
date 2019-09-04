import { TestBed, inject } from '@angular/core/testing';

import { KnowledageManageServiceService } from './knowledage-manage-service.service';

describe('KnowledageManageServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KnowledageManageServiceService]
    });
  });

  it('should be created', inject([KnowledageManageServiceService], (service: KnowledageManageServiceService) => {
    expect(service).toBeTruthy();
  }));
});
