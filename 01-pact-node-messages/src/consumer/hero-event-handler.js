const HeroCreatedEvent = require('../common/hero-created-event');

exports.HeroEventHandler = {
  handleHeroCreatedEvent: message => {
    HeroCreatedEvent.validateId(message);
    HeroCreatedEvent.validateName(message);
    HeroCreatedEvent.validateSuperpower(message);
    HeroCreatedEvent.validateUniverse(message);

    // ... pass the event into domain logic
  },
};
