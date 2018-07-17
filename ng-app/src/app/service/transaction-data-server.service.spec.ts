import { TestBed, inject } from '@angular/core/testing';

import { TransactionDataServerService } from './transaction-data-server.service';

describe('TransactionDataServerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TransactionDataServerService]
    });
  });

  it('should be created', inject([TransactionDataServerService], (service: TransactionDataServerService) => {
    expect(service).toBeTruthy();
  }));
});
