var express = require("express");
var router = express.Router();

var ip = require("../controllers/ip.controller.js");

router
	.route("/checkIP")
	.post(ip.checkIP);