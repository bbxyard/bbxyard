var mongoose = require("mongoose");
var Schema = mongoose.Schema;



/**
 * User table
 */
var UserSchema = new Schema({
	userid: String,
	passwd: String,
	date: { type: Date, default: Date.now() }
});
UserSchema.methods.speak = function() {
	var s = this.userid ? "My name is " + this.userid : "I don't have a name";
	console.log(s);
}
User = mongoose.model("users", UserSchema);



/**
 * Word table
 */
var WordSchema = new Schema({
	name: String,
	mean: String,
	memo: String
});
Word = mongoose.model("words", WordSchema);


/**
 * exports
 * @type {Model|*|Aggregate}
 */
exports.User = User;
exports.Word = Word;
