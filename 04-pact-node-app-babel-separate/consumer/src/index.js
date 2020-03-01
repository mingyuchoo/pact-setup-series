import axios from 'axios';

axios
  .post('http://localhost:3000/heroes', {})
  .then(res => {
    console.log(res.data);
  })
  .catch(err => {
    console.log(err);
  });
