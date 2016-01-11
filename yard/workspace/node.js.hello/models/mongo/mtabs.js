var mongoose=require("mongoose");
var Schema = mongoose.Schema;

// callback
function on_save(err, c) {
	if (err)
		return console.error(err);
	console.log(c);	
}


// User table
var user_schema = new Schema({
	_id:    String,
	userid: String,
	passwd: String
});

user_schema.methods.speak = function() {
	var s = this.userid ? "My name is " + this.userid : "I don't have a name";
	console.log(s);
}


User = mongoose.model("users", user_schema);
// User.prototype.save = function(err, u) {
// 	this.save(function(err, u) {
// 		on_save(err, u);
// 	});
// }


// New Words table
var word_schema = new Schema({
	_id:  String,
	name: String,
	mean: String,
	memo: String
});
Word = mongoose.model("words", word_schema);


// ...
// exports
// exports.on_save;
exports.User = User;
exports.Word = Word;
