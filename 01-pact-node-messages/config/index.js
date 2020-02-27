const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/config.json')[env];

module.exports = config;
