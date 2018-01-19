var mongoose = require("mongoose");

var ipSchema = new mongoose.Schema({
	ipaddress = {
		type : String,
		required : true
	}
});

mongoose.model("IPAddress", ipSchema);