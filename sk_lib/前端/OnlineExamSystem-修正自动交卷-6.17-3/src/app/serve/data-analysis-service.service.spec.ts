import { TestBed, inject } from '@angular/core/testing';

import { DataAnalysisServiceService } from './data-analysis-service.service';

describe('DataAnalysisServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DataAnalysisServiceService]
    });
  });

  it('should be created', inject([DataAnalysisServiceService], (service: DataAnalysisServiceService) => {
    expect(service).toBeTruthy();
  }));
});
