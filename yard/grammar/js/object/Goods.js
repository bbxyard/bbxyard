var Goods = {
    create : function(name, price) {
        function GoodsRep(name, price) {
            this.name = name;
            this.price = price;
        }
        GoodsRep.prototype.show = function() {
            console.log("Goods: %s price %.2f", this.name, this.price);
        }
        GoodsRep.prototype.incPrice = function(delta) {
            this.price += delta;
        }
        var obj = new GoodsRep(name, price);
        return obj;
    }
}

var Goods2 = {
    name: "xx",
    price: 0.0,
    show: function() {
        console.log("Good2: %s price %f", this.name, this.price);
    },
    decPrice: function(delta) {
        this.price -= delta;
    }
}

function Goods3() {}
Goods3.prototype.BJ = {};
Goods3.prototype.DE = {
    show: function() {
        console.log("you got one goods from Deut!");
    }
}

var g4 = function() {}
g4.prototype.CN = {"hallo": "你好", "Mine": "我的"};
g4.prototype.DE = {
    show: function() {
        console.console.log("Goods from FOUR");
    }
}


function Goods5(name, price) {
    name = name || "foo";
    price = price || 368;
    console.log(this);
    this.Data = {"name": name, "price": price };
    console.log(this);
    this.Attr.style = this.Attr.style + "-2017";
    console.log(this);
    console.log("__proto__:", __proto__);
}

Goods5.prototype.Attr = { "color": "red", "style": "fashion" };

Goods5.prototype.XXXX = {
    display: function (argument) {
        console.log(__proto__.Attr);
        console.log(this);
    }
}

Goods5.prototype.show = function() {
    console.log("Inside->Good5.Data: ", this.Data);
    console.log("Inside->Good5.Attr: ", this.Attr);
}

var goods = Goods.create("jade", 108.69);
goods.show();
var goods2 = Object.create(Goods2);
goods2.name = "gold";
goods2.price = 268;
goods2.show();

var goods3 = new Goods3();
goods3.DE.show();
console.log(g4.CN);
//g4.DE.show();

var g5 = new Goods5();
g5.show();
console.log("Outside->Good5.Data: ", g5.Data);
console.log("Outside->Good5.Attr: ", g5.Attr);
g5.XXXX.display();
