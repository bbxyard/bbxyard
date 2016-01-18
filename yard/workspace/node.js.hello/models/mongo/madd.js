var mongoose = require("mongoose");
var User = require("./mtabs").User;
var Word = require("./mtabs").Word;


function doConnect(uri) {
	mongoose.connect(uri);
	var db = mongoose.connection;
	db.on("error", console.error.bind(console, "connection error:"));
	db.once("open", function(callback) {
		// ...
		console.log("mongodb connected");
	});
	console.log("hello mongodb");
	return db;
}

// callback
function onCollectionSave(err, c) {
	if (err)
		return console.error(err);
	console.log(c);	
}


function doAdd() {
	var user = new User({_id:"20151112.5u1", userid:"990719", "passwd":"123456"});
	var word = new Word({_id:"20151112.5w1", name:"hallo", mean:"你好", memo:"this is a test"});
	word.save(function(err, w) { onCollectionSave(err, w)});
	user.save(function(err, u) { onCollectionSave(err, u)});
	user.speak();
}

function doQuery() {

	User.find({}, function(err, docs) {
		docs.forEach(function(doc, idx, array) {
			console.log("index %d: ", idx, doc);
		});
	});

	
	// Person.
  // find({
    // occupation: /host/,
    // 'name.last': 'Ghost',
    // age: { $gt: 17, $lt: 66 },
    // likes: { $in: ['vaporizing', 'talking'] }
  // }).
  // limit(10).
  // sort({ occupation: -1 }).
  // select({ name: 1, occupation: 1 }).
  // exec(callback);
	
	// User.find({userid:"990719"})
	// 	.limit(10)
	// 	.sort({ passwd: -1 })
	// 	.select( {passwd: 1, userid: 1} )
	// 	.exec(callback);
}


/**
 * main
 */
var db = doConnect("mongodb://localhost/bbx");
doAdd();
doQuery();