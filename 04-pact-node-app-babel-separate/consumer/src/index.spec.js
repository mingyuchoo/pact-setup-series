import { describe, it } from 'mocha';
import { expect } from 'chai';
import { Pact } from '@pact-foundation/pact';

import path from 'path';

describe('message consumer', () => {
  const mockPact = new Pact({
    consumer: 'consumer',
    provider: 'provider',
    dir: path.resolve(process.cwd(), 'pact_contracts'),
    pactfileWriteMode: 'update',
    logLevel: 'info',
  });

  const EXPECTED_BODY = {};

  describe('Index', () => {
    it('createHero', done => {
      mockPact.setup().then(() => {
        mockPact.addInteraction({
          state: 'I have a list of projects',
          uponReceiving: '',
        });
      });
    });
  });
});
