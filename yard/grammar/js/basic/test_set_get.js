// test access set/get


var PersonA = {
    _age: 0,
    set age(value) {
        this._age = (value > 100)?
              new Date().getFullYear() - value:
              value;
    },
    get age() {
        return this._age;
    },
    show: function() {
        console.log("AGE: " + this.age);
    }
};

function PersonB(name) {
    var age = 2000;
    this._name = name;
    Object.defineProperty(this, "age", {
        get: function () {
            console.log("内部存储数据为:" + age);
            return new Date().getFullYear() - age;
        },
        set: function (value) { age = value; }
    });
    Object.defineProperty(this, "name", {
        get: function () {
            console.log("Mine Name ist :" + this._name);
            return this._name;
        },
        set: function (value) { this._name = value; }
    });
}

var p1 = PersonA;
p1.age = 1985;
console.log("from get: ", p1.age);
PersonA.show();

var p2 = new PersonB("pufan66");
console.log("from get: " + p2.name, p2.age);
p2.age = 1988;
p2.name = "boxu";
console.log("from get: " + p2.name, p2.age);
