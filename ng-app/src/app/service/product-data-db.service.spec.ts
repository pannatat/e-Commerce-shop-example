import { TestBed, inject } from '@angular/core/testing';

import { ProductDataDbService } from './product-data-db.service';

describe('ProductDataDbService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductDataDbService]
    });
  });

  it('should be created', inject([ProductDataDbService], (service: ProductDataDbService) => {
    expect(service).toBeTruthy();
  }));
});
