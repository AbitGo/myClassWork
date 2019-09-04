import { TestBed, inject } from '@angular/core/testing';

import { DesignPaperService } from './design-paper.service';

describe('DesignPaperService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DesignPaperService]
    });
  });

  it('should be created', inject([DesignPaperService], (service: DesignPaperService) => {
    expect(service).toBeTruthy();
  }));
});
