import { TestBed, inject } from '@angular/core/testing';

import { TableServiceService } from './table-service.service';

describe('TableServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TableServiceService]
    });
  });

  it('should be created', inject([TableServiceService], (service: TableServiceService) => {
    expect(service).toBeTruthy();
  }));
});
