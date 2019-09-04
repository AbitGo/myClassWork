import { TestBed, inject } from '@angular/core/testing';

import { ExamPageServiceService } from './exam-page-service.service';

describe('ExamPageServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExamPageServiceService]
    });
  });

  it('should be created', inject([ExamPageServiceService], (service: ExamPageServiceService) => {
    expect(service).toBeTruthy();
  }));
});
