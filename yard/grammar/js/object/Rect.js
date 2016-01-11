// Javascript object 

function Rect(length, width) {
    this.length = length || 1;
    this.width  = width || 1;
    this.corlor = "dark";
}

// get readonly interface
Rect.prototype.getLength = function() {
    return this.length;
}
Rect.prototype.getWidth = function() {
    return this.width;
}
Rect.prototype.getColor = function() {
    return this.color;
}
Rect.prototype.getArea  = function() {
    return this.length * this.width;
}

// update interface
Rect.prototype.update   = function(l, w) {
    this.length = l;
    this.width  = w;
}
Rect.prototype.assign   = function(r) {
    this.length = r.length;
    this.width  = r.width;
}

// show
Rect.prototype.show     = function() {
    console.log("rect (length, width) = (" + this.getLength() + ", " + this.getWidth() + ")");
    console.log("rect area is: " + this.getArea());
}

// demo
function demo() {
    console.log("test rect");
    var rect = new Rect(5, 4);
    rect.show();
    
    var rect0 = {"length":50, "width":40};  // 更新by原始json
    rect.assign(rect0);
    rect.show();
    
    var rect1 = new Rect(7, 18);            // 更新by-copy构造
    rect.assign(rect1);
    rect.show();  
}

// show demo
demo();
