var express  = require('express');
var bodyParser = require('body-parser');
var app      = express();
var port     = process.env.PORT || 8080;

// Configuration
app.use(express.static(__dirname + '/public'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

// Routes
require('./routes/routes.js')(app);

// Run
app.listen(port);
console.log('The App runs on port ' + port);