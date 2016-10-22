var mongoose = require('mongoose');

var SneezeSchema = new mongoose.Schema({
    user_id: String,
    timestamp: {type: Date, default: Date.now},
});

module.exports = mongoose.model('Sneeze', SneezeSchema);
