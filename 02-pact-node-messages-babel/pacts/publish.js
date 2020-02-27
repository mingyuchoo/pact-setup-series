import config from '../config';
import publisher from '@pact-foundation/pact-node';
import path from 'path';

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
