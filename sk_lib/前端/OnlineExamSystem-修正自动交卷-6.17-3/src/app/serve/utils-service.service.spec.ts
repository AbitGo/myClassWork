import { TestBed, inject } from '@angular/core/testing';

import { UtilsServiceService } from './utils-service.service';

describe('UtilsServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UtilsServiceService]
    });
  });

  it('should be created', inject([UtilsServiceService], (service: UtilsServiceService) => {
    expect(service).toBeTruthy();
  }));
});
