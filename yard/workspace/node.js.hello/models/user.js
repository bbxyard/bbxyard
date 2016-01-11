var mongoose=require("mongoose");
var Schema = mongoose.Schema;
var user_schema = new Schema({
	userid: String,
	passwd: String
});
exports.user = mongoose.model("users", user_schema);

