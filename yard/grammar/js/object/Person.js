
function Person(name, age, num) {
	this.name	= name;
	this.age	= age;
	this.num	= num;
	
	this.show	= show;
	function show() {
		console.log("My name is: " + this.name + " and " + this.age + "; call me: " + this.num + ".");
	}
}

Person.prototype.update = function(name, age, num) {
	this.name = name;
	this.age  = age;
	this.num  = num;
}

Person.prototype.info = function() {
	var s = ("My name is: " + this.name + " and " + this.age + "; call me: " + this.num + ".");
	return s;
}

var person = new Person("boxu", 30, "135-8150-6320");
person.show();
console.log(person.info());
person.update("Brian", 2, "138-6593-3383");
console.log(person.info());
