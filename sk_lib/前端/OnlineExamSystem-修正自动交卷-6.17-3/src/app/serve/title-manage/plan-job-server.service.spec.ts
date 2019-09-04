import { TestBed, inject } from '@angular/core/testing';

import { PlanJobServerService } from './plan-job-server.service';

describe('PlanJobServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlanJobServerService]
    });
  });

  it('should be created', inject([PlanJobServerService], (service: PlanJobServerService) => {
    expect(service).toBeTruthy();
  }));
});
