// test ep
//var EventProxy = require("../app/eventproxy.js");
var debug = require("debug");
var EventProxy = require("eventproxy");

function testRepeatFunc() {
    var ep = new EventProxy();
    console.log(ep);
}

testRepeatFunc();
