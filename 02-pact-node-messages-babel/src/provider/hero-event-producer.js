import HeroCreatedEvent from '../common/hero-created-event';

exports.CreateHeroEventProducer = {
  produceHeroCreatedEvent: () => {
    return new Promise((resolve, reject) => {
      resolve(new HeroCreatedEvent(42, 'Superman', 'Flying', 'DC'));
    });
  },
};
