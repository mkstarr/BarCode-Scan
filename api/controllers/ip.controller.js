var mongoose = require("mongoose");
var IPAddress = mongoose.model("IPAddress");

//Preconfined IP Addresses, for the purpose of this project
var IPs = [221.221.12.34, 221.221.12.35, 221.221.12.36, 221.221.12.37, 221.221.12.38, 221.221.12.39, 
			221.221.12.40, 221.221.12.41, 221.221.12.42, 221.221.12.43, 221.221.12.44, 221.221.12.45, 
			221.221.12.46, 221.221.12.47, 221.221.12.48, 221.221.12.49, 221.221.12.50, 221.221.12.51];

module.exports.checkIP = function(req, res){
	var ip = req.body.IP;
	if(IPs.indexOf(ip) != -1){
		//Generate a random local IP(unique key) for purpose of this project
		var part1 = "";
		var part2 = "";
		var part3 = "";
		var part4 = "";
		var possible = "0123456789";
					
		for (var i = 0; i < 3; i++)
			part1 += possible.charAt(Math.floor(Math.random() * possible.length));

		for (var i = 0; i < 3; i++)
			part2 += possible.charAt(Math.floor(Math.random() * possible.length));

		for (var i = 0; i < 2; i++)
			part3 += possible.charAt(Math.floor(Math.random() * possible.length));

		for (var i = 0; i < 2; i++)
			part4 += possible.charAt(Math.floor(Math.random() * possible.length));

		//The unique key to be saved in database
		var key = part1 + "." + part2 + "." + part3 + "." + part4;

		IPAddress
			.create({
				ipaddress : key
			}, function(err, ip){
				if(err)
				{
					console.log("Could not record machine in database");
					res
						.status(400)
						.json(err);
				}
				else
				{
					console.log("Machine recorded in database");
					res
						.status(201)
						.json("local_ip" : key);
				}
			});
	}
};