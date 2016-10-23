var express = require('express');
var router = express.Router();

var Sneeze =  require("../models/Sneeze.js");

/* GET home page. */
router.get('/', function(req, res, next) {
  Sneeze.count(function(err, c) {
    Sneeze.distinct("user_id", function(err, r) {
      res.render('index', { userCount: r.length, sneezeCount: c, matchCount: Number((r.length/2).toFixed(0))});	
    });
  });
});

module.exports = router;
