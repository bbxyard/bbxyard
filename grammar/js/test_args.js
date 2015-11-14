// this is js test for Arguments


function doTestArguments() {
    var cnt = arguments.length;
    for (var i = 0; i < cnt; ++i) {
        var s = arguments[i];
        console.log("the args " + (i + 1) + ": is " + s);
    }
}

doTestArguments("hello", "boxu", 2015, 11, 14);
doTestArguments("world", "boxu", 1985, 7, 18);
