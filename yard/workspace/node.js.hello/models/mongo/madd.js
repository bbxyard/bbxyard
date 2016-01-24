var mongoose = require("mongoose");


/**
 * MongoUtil
 * @constructor
 */
function MongoUtil() {

	// public functions
	this.doAdd = doAdd;
	this.doImport = doImport;
	this.doExport = doExport;

	/**
	 *
	 * @param uri
	 * @param cname
	 * @param infile
	 * @param outJsonFile
     */
	function doAdd(uri, cname, infile, outJsonFile) {
		connectOnly(uri).once("open", function() {
			console.log("uri=%s, infile=%s, cname=%s, outfile=%s", uri, infile, cname, outJsonFile);
			doAddTest();
			//onFinished();
		});
	}

	function doImport(uri, cname, infile) {
		var db = connectOnly(uri);
		db.once("open", function() {

		})
	}

	function doExport(uri, cname, outfile) {
		var db = connectOnly(uri);
		db.once("open", function() {

		})
	}


	/**
	 * @type private functions
     */
	function connectOnly(uri) {
		mongoose.connect(uri);
		var db = mongoose.connection;
		db.on("error", console.error.bind(console, "connection error:"));
		console.log("mongodb started");
		return db;
	}
	function onFinished() {
		mongoose.disconnect();
	}
}

/**
 * MongoQuickDemo one quick demo
 * Usage: new MongoQuickDemo().demo();
 * @constructor
 */
function MongoQuickDemo() {
	this.demo = demo;
	function demo() {
		console.log("MongoQuickDemo start!!");
		main("mongodb://localhost/bbx", run);
		console.log("MongoQuickDemo finished!!");
	}

	function main(uri, cb) {
		mongoose.connect(uri);
		var db = mongoose.connection;
		db.on("error", console.error.bind(console, "connection error:"));
		console.log("hello mongodb");
		db.once("open", cb);
		return db;
	}
	function run() {
		console.log("mongodb connected!!");
		// todo sth
	}
}



function doAddTest() {
	// callback
	function onCollectionSave(err, c) {
		if (err)
			return console.error(err);
		console.log(c);
	}

	var User = require("./mgschemas").Query("User");
	var Word = require("./mgschemas").Query("Word");
	var user = new User({userid:"990719", "passwd":"123456"});
	var word = new Word({name:"hallo", mean:"你好", memo:"this is a test"});
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
 * MongoAppMain App Main router
 * @constructor
 */
function MongoAppMain() {
	var mu = new MongoUtil();
	var program = require("commander");
	program
		.version("1.0.0")
		.option("-U, --uri <path>", "the mongo db uri")
		.option("-L, --logfile <path>", "the operation log file")
	program
		.command("add <inListFile>")
		.alias("a")
		.description("add from the list, maybe only part field")
		.option("-o, --outJsonFile [file]", "output json log file")
		.option("-c, --cname <cname>", "collection name")
		.action(function(inListFile, options){
			var uri = options.uri || "mongodb://localhost/bbx";
			var cname = options.cname || "users";
			inListFile = inListFile || "in.lst";
			var outJsonFile = options.outJsonFile || "out.json";
			mu.doAdd(uri, options.cname, inListFile, outJsonFile);
		});
	program
		.command("import [inJsonFile]")
		.alias("im")
		.description("import from the full fielded json file")
		.option("-c, --cname <cname>", "collection name")
		.action(function(inJsonFile, options) {
			inJsonFile = inJsonFile || "in.json";
			console.log("infile=%s, cname=%s", inJsonFile, options.cname);
		})
		.on("--help", function() {
			console.log("  Examples: ");
			console.log();
			console.log("	xx import -c users 1.json");
			console.log("	xx import 1.json");
			console.log();
		});
	program
		.command("*")
		.action(function(cmd) {
			console.log("unknown command %s", cmd);
			this.help();
		});

	program.parse(process.argv);
}



//doAdd();
//doQuery();
// new MongoQuickDemo().demo();

MongoAppMain();

var GetSchema = require("./mgschemas").GetSchema;
var User2 = GetSchema("user");
var user2 = new User2();
console.log(user2);

var Word = require("./mgschemas").Query("Word");
//console.log(Word);

var XXXX = require("./mgschemas").Query("XXXX");
console.log(XXXX);