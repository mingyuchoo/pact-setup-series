const config = require('../config');

const publisher = require('@pact-foundation/pact-node');
const path = require('path');

const opts = {
  pactFilesOrDirs: [path.resolve(process.cwd(), 'pacts')],
  pactBroker: config.pactBrokerUrl,
  pactBrokerUsername: config.pactBrokerUsername,
  pactBrokerPassword: config.pactBrokerPassword,
  consumerVersion: config.consumerVersion,
};

publisher
  .publishPacts(opts)
  .then(() => console.log('Pacts successfully published'));
