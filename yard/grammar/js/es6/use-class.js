// ES6
class Box {
  constructor(length, width) {
    this.length = length;
    this.width = width;
  }
  calculateArea() {
    return this.length * this.width;
  }
}

let box = new Box(2, 2);
box.calculateArea(); // 4

// ES5
function Box(length, width) {
  this.length = length;
  this.width = width;
}

Box.prototype.calculateArea = function() {
  return this.length * this.width;
}

var box = new Box(2, 2);
box.calculateArea(); // 4

/**
 *
 ES6 class只不过是现有的基于原型继承机制的一层语法糖，了解这个事实之后，
 class关键字对你来说就不再像一个其它语言的概念了。
 *
**/
