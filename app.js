var express = require("express");
var app = express();
var path = require("path");

app.set("port", 3000);

/////////var routes = require("./api/routes");

var bodyParser = require("body-parser");

// MIDDLEWARE
app.use(function(req, res, next){
	console.log(req.method, req.url);
	next();
});

app.use(bodyParser.urlencoded({ extended : false }));    //req.body will not work without body-parser
app.use(bodyParser.json());

var server = app.listen(app.get("port"), function(){
	var port = server.address().port;
	console.log("Running on port " + port);
});

