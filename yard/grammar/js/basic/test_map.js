// map test


function testMapSimple() {
    var data = [1, 2, 3, 4];
    var arrayOfSquares = data.map(function (item) {
        return item * item;
    });
    console.log("data: %s -> %s", data.toString(), arrayOfSquares.toString());
}

function testMapAttr() {
    var users = [
      {name: "张含韵", "email": "zhang@email.com"},
      {name: "江一燕",   "email": "jiang@email.com"},
      {name: "李小璐",  "email": "li@email.com"}
    ];
    var names = users.map(function(item) { return item.name; });
    var emails = users.map(function(item){ return item.email; });
    console.log("names: ", names);
    console.log("emails: ", emails.join(", "));
}

testMapSimple();
testMapAttr();
