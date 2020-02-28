// const { describe, it } = require('mocha');
// const { expect } = require('chai');

// const config = require('../../../config');

// const { MessageProviderPact } = require('@pact-foundation/pact');
// const { CreateHeroEventProducer } = require('../hero-event-producer');
// const path = require('path');

import { describe, it } from 'mocha';
import { expect } from 'chai';

import config from '../../config';

import { MessageProviderPact } from '@pact-foundation/pact';
import { CreateHeroEventProducer } from '../hero-event-producer';
import path from 'path';

describe('message producer', () => {
  const messagePact = new MessageProviderPact({
    messageProviders: {
      'a hero created message': () =>
        CreateHeroEventProducer.produceHeroCreatedEvent(),
    },
    log: path.resolve(process.cwd(), config.logPath, config.logFileName),
    logLevel: config.logLevel,
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
