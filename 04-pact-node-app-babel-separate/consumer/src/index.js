import axios from 'axios';

export const createHero = (baseUrl, port, hero) => {
  axios
    .request({
      method: 'POST',
      url: `/heroes`,
      baseURL: `${baseUrl}:${port}`,
      headers: {
        Accept: 'application/json; charset=utf-8',
        'Content-Type': 'application/json; charset=utf-8',
      },
      data: hero,
    })
    .then(res => {
      console.log(res.data);
    })
    .catch(err => {
      console.log(err);
    });
};

createHero('http://localhost', 3000, {});
