import { TestBed, inject } from '@angular/core/testing';

import { LoginServeService } from './login-serve.service';

describe('LoginServeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginServeService]
    });
  });
  it('should be created', inject([LoginServeService], (service: LoginServeService) => {
    expect(service).toBeTruthy();
  }));
});
