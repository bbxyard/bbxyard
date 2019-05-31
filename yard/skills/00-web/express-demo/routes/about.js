var express = require('express');
var router = express.Router();

router.get('/about', function(req, res, next) {
  res.send('Hallo world');
  // res.render('index', { title: 'About' });
});

module.exports = router;
