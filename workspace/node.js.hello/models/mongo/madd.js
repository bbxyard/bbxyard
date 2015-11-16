var mongoose = require("mongoose");
var User = require("./mtabs").User;
var Word = require("./mtabs").Word;

function do_connect(uri) {
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
function on_collection_save(err, c) {
	if (err)
		return console.error(err);
	console.log(c);	
}


function do_add() {
	var user = new User({_id:"20151112.5u9", userid:"990719", "passwd":"123456"});
	var word = new Word({_id:"20151112.5w9", name:"hallo", mean:"你好", memo:"this is a test"});
	word.save(function(err, w) { on_collection_save(err, w)});
	user.save(function(err, u) { on_collection_save(err, u)});
	user.speak();
}

function do_query() {
	
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

var db = do_connect("mongodb://localhost/test");

//do_add();
////do_query();

// doc.array = [1,2,3];

//  var shifted = doc.array.$shift();
//  console.log(shifted); // 1
//  console.log(doc.array); // [2,3]

//  // no affect
//  shifted = doc.array.$shift();
//  console.log(doc.array); // [2,3]

//  doc.save(function (err) {
//    if (err) return handleError(err);

//    // we saved, now $shift works again
//    shifted = doc.array.$shift();
//    console.log(shifted ); // 2
//    console.log(doc.array); // [3]
//  })