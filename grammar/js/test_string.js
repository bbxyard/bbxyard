/**
 * string test
 * author:  Brian
 * date:    2015.11.15
 */
 
// #1 transform case
function doCaseTest(str) {
    console.log("#1 toUpperCase: " + str.toUpperCase());
    console.log("#1 toLowerCase: " + str.toLowerCase());
}

// #2 trim blank
function doTrimTest(s) {
    var o = s.trim();
    s.print("#2 trim[old]: ");
    o.print("#2 trim[new]: ");
    o.print();
}

// # 3 Split
function doSplit(s) {
    var a = s.split(" ");
    for (var i = 0, len = a.length; i < len; ++i) {
        a[i].print("#3 test split: ");
    }
}

// # 4 Find
function doIndex(s, w) {
    var posB = s.indexOf(w);
    var posE = s.lastIndexOf(w);
    console.log("#4 from #" + s + "# search #" + w + "# first is: " + posB);
    console.log("#4 from #" + s + "# search #" + w + "# last is: " + posE);
}

// # 5 replace
function doRelpace(s, wsrc, wdst) {
    var o = s.replace(wsrc, wdst);
    s.print("#5 replace[old]: ");
    o.print("#5 replace[new]: ");
}

// # 


String.prototype.print = function(prompt) {
    var p = prompt || "";
    console.log(p + "#" + this + "# and length is " + this.length + " bytes");
}

function demo(str) {
    // # 1 case transform
    doCaseTest(str);
    
    // # 2 test trim
    var s = new String("  hello world  \n");
    doTrimTest(s);
    
    // # 3 split
    doSplit(str);
    
    // # 4 indexOf lastIndexOf
    doIndex("this is a very test!", "is");
    
    // # 5 replace
    doRelpace("Do you know Brian?", "you", "they");
}

demo("Hallo world und JS");
