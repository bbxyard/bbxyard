var mongoose = require("mongoose");
var Schema = mongoose.Schema;



/**
 * User table
 */
var UserSchema = new Schema({
	_id:    String,
	userid: String,
	passwd: String
});
UserSchema.methods.speak = function() {
	var s = this.userid ? "My name is " + this.userid : "I don't have a name";
	console.log(s);
}
User = mongoose.model("users", UserSchema);



// New Words table
var WordSchema = new Schema({
	_id:  String,
	name: String,
	mean: String,
	memo: String
});
Word = mongoose.model("words", WordSchema);


// ...
// exports
// exports.onSave;
exports.User = User;
exports.Word = Word;



// callback
//function onSave(err, c) {
//	if (err)
//		return console.error(err);
//	console.log(c);
//}
// User.prototype.save = function(err, u) {
// 	this.save(function(err, u) {
// 		on_save(err, u);
// 	});
// }
