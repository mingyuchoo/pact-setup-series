import { describe, it } from 'mocha';
import { expect } from 'chai';

const { MessageProviderPact } = require('@pact-foundation/pact');
const { CreateHeroEventProducer } = require('./hero-event-producer');
const path = require('path');

describe('message producer', () => {
  const messagePact = new MessageProviderPact({
    messageProviders: {
      'a hero created message': () =>
        CreateHeroEventProducer.produceHeroCreatedEvent(),
    },
    log: path.resolve(process.cwd(), 'logs', 'pact.log'),
    logLevel: 'info',
    provider: 'node-message-provider',
    pactBrokerUrl: 'http://localhost:9292',
    pactBrokerUsername: 'admin',
    pactBrokerPassword: 'admin',
  });

  describe("'hero created' message producer", () => {
    it('should create a valid hero created message', done => {
      messagePact.verify().then(
        () => done(),
        error => done(error),
      );
    }).timeout(5000);
  });
});
