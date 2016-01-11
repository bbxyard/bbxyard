var mongodb = require('mongodb');
var server  = new mongodb.Server("localhost", 27017, {auto_reconnect:true});
var db	    = new mongodb.Db("bbx", server, {safe:false});

console.error("hwllo world");
console.log("this is a test");

// connect db
db.open(function(err, db) {
	if (err) {
		console.error("connect db failed" + err);
		return;
	}
	console.log("connect db OK");

	db.collection("users", {safe:false}, function(err, users) {
		var tmp1 = {"userid":"boxu", "passwd":"1234"};
		var tmp2 = {userid:"pufan6",  passwd:"12345"};
		users.insert([tmp1, tmp2], {safe:true}, function(err, result) {
		console.log(result);
		});
	});
});

