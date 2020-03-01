import express from 'express';
import bodyParser from 'body-parser';
import logger from 'morgan';

const app = express();

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.post('/heroes', (req, res) => {
  res.status(201);
  res.json({
    id: 42,
    superpowre: 'flying',
    name: 'Superman',
    universe: 'DC',
  });
});

app.listen(3000, () => console.log(`provider listening on port 3000!`));
