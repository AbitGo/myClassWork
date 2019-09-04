import { TestBed, inject } from '@angular/core/testing';

import { ImportTitleService } from './import-title.service';

describe('ImportTitleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ImportTitleService]
    });
  });

  it('should be created', inject([ImportTitleService], (service: ImportTitleService) => {
    expect(service).toBeTruthy();
  }));
});
