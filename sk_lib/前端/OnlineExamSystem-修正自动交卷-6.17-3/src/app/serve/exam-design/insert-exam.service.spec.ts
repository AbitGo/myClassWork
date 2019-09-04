import { TestBed, inject } from '@angular/core/testing';

import { InsertExamService } from './insert-exam.service';

describe('InsertExamService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InsertExamService]
    });
  });

  it('should be created', inject([InsertExamService], (service: InsertExamService) => {
    expect(service).toBeTruthy();
  }));
});
