// define class
class Point {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
  toString() {
    return "(" + this.x + ', ' + this.y + ")";
  }
  display() {
    console.log(this.toString());
  }
}

// extend sub class
class ColorPoint extends Point {
  constructor(x, y, color) {
    super(x, y);
    this.color = color;
  }
  toString() {
    return this.color + " " + super.toString();
  }
}

var point = new Point(3, 4);
point.display();

var point2 = new ColorPoint(3, 4, "red");
point2.display();
