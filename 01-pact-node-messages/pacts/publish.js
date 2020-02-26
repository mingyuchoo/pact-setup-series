let publisher = require('@pact-foundation/pact-node');
let path = require('path');

let opts = {
  pactFilesOrDirs: [path.resolve(process.cwd(), 'pacts')],
  pactBroker: 'http://localhost:9292',
  pactBrokerUsername: 'admin',
  pactBrokerPassword: 'admin',
  consumerVersion: '3.5.0',
};

publisher
  .publishPacts(opts)
  .then(() => console.log('Pacts successfully published'));
