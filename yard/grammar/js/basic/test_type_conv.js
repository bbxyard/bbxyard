// test type convert

function testInt2Str(n) {
    console.log("n %d -> %s(08)", n, n.toString(8));
    console.log("n %d -> %s(10)", n, n.toString(10));
    console.log("n %d -> %s(16)", n, n.toString(16).toUpperCase());
}

function testStr2Int(str) {
    console.log("str %s -> %d(08)", str, parseInt(str, 8));
    console.log("str %s -> %d(10)", str, parseInt(str, 10));
    console.log("str %s -> %d(16)", str, parseInt(str, 16));
}

function toString16(n, pad_digit) {
    var s = "00000000" + n.toString(16).toUpperCase();
    var r = s.slice(pad_digit * (-1));
    return r;
}

function testJson2Obj() {
    var jstr = '{"name":"Brian", "score":59, "enabled": true, "hobby":"english"}';
    var obj = JSON.parse(jstr);
    var obj2 = {"name":"Brian", score:69, "disabled":false, hobby: "English" };
    var jstr2 = JSON.stringify(obj2);
    console.log("jstr: ", jstr);
    console.log("JSON.parse-d: ", obj);
    console.log("json object: ", obj2);
    console.log("JSON.stringify-d: ", jstr2);
}

testInt2Str(256);
testInt2Str(500);
testInt2Str(65535);
testStr2Int("100");
console.log("###16 string: " + toString16(501, 6));
testJson2Obj();
