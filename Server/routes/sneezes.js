var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var Sneeze =  require("../models/Sneeze.js");

/* GET /sneezes listing. */
router.get('/', function(req, res, next) {
  Sneeze.find(function (err, sneezes) {
    if (err) return next(err);
    res.json(sneezes);
  });
});

/* POST /sneezes */
router.post('/', function(req, res, next) {
    Sneeze.create(req.body, function (err, post) {
        if (err) return next(err);
        res.json(post);
    });
});

/* GET /sneezes/id */
router.get('/:id', function(req, res, next) {
    Sneeze.findById(req.params.id, function (err, post) {
        if (err) return next(err);
        res.json(post);
    });
});

/* PUT /sneezes/:id */
router.put('/:id', function(req, res, next) {
    Sneeze.findByIdAndUpdate(req.params.id, req.body, function (err, post) {
        if (err) return next(err);
        res.json(post);
    });
});

/* DELETE /sneezes/:id */
router.delete('/:id', function(req, res, next) {
    Sneeze.findByIdAndRemove(req.params.id, req.body, function (err, post) {
        if (err) return next(err);
        res.json(post);
    });
});
var range = 5*60*1000;
/* POST /sneezes/match/:id */
router.post('/match/:timestamp', function(req, res, next) {
    var timeToCheck = new Date(req.params.timestamp);
    var start = +timeToCheck + range/2;
    var end = +timeToCheck - range/2;
    Sneeze.find({"timestamp": {'$gte': end, '$lte': start }}, function (err, post) {
        if (err) return next(err);
        var minDist = post[0] - timeToCheck;
        var minSneeze = post[0];
        for(var x = 1; x < post.length; x++) {
            var diff = post[x] - timeToCheck;
            if(diff < minDist && -diff < minDist) {
                minDist = diff;
                minSneeze = post[x];
            }
        }
        res.json(minSneeze);
    });
});

module.exports = router;
