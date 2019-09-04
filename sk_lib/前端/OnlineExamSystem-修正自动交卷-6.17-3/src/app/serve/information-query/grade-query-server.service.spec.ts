import { TestBed, inject } from '@angular/core/testing';

import { GradeQueryServerService } from './grade-query-server.service';

describe('GradeQueryServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GradeQueryServerService]
    });
  });

  it('should be created', inject([GradeQueryServerService], (service: GradeQueryServerService) => {
    expect(service).toBeTruthy();
  }));
});
