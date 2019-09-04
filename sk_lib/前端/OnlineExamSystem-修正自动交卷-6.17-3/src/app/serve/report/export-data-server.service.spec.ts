import { TestBed, inject } from '@angular/core/testing';

import { ExportDataServerService } from './export-data-server.service';

describe('ExportDataServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExportDataServerService]
    });
  });

  it('should be created', inject([ExportDataServerService], (service: ExportDataServerService) => {
    expect(service).toBeTruthy();
  }));
});
