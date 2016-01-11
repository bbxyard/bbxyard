// this is js test for Arguments


function doTestArguments() {
    var cnt = arguments.length;
    for (var i = 0; i < cnt; ++i) {
        var s = arguments[i];
        console.log("the args " + (i + 1) + ": is " + s);
    }
}

function doTestArguments2() {
    for (x in arguments) {
        var s = arguments[x];
        console.log("the args " + (x) + ": is " + s);
    }
}

function printCmdline(argv) {
    for (var i = 0; i < argv.length; ++i) {
        console.log(argv[i]);
    }
    argv.forEach(function(val, index, array) {
        console.log("index " + index + ": " + val);
    })
}

doTestArguments("hello", "boxu", 2015, 11, 14);
doTestArguments2("world", "boxu", 1985, 7, 18);
printCmdline(process.argv.slice(2));
