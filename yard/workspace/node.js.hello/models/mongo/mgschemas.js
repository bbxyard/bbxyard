/**
 * define useful mongole schemas for MongoDB operation
 */
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
 * GetSchema return the schema in this module
 * @param name  schema name
 * @returns {*}
 * @constructor
 */
var GetSchema = function(name) {
    console.log("Get Schema... %s", name);
    var m = {};
    m["user"] = m["User"] = User;
    m["word"] = m["Word"] = Word;
    return m[name];
};


/**
 * exports
 * @type {Model|*|Aggregate}
 */
exports.GetSchema = GetSchema;
exports.Query     = GetSchema;
