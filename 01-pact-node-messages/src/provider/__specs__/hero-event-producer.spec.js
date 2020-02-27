const { describe, it } = require('mocha');
const { expect } = require('chai');

const config = require('../../../config');

const { MessageProviderPact } = require('@pact-foundation/pact');
const { CreateHeroEventProducer } = require('../hero-event-producer');
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
    pactBrokerUrl: config.pactBrokerUrl,
    pactBrokerUsername: config.pactBrokerUsername,
    pactBrokerPassword: config.pactBrokerPassword,
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
