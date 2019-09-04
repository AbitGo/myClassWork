import { TestBed, inject } from '@angular/core/testing';

import { ErrorTitleService } from './error-title.service';

describe('ErrorTitleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ErrorTitleService]
    });
  });

  it('should be created', inject([ErrorTitleService], (service: ErrorTitleService) => {
    expect(service).toBeTruthy();
  }));
});
