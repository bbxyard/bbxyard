// 类型测试

function testAutoArray(foo) {
    console.log("typeof foo is:" + typeof foo);
    if (foo instanceof Array) {
        console.log('Congrats, you have an array! BY instanceof');
    }
    if (Array.isArray(foo)) {
        console.log('Congrats, you have an array! BY isArray');
    }
}

function testAutoType() {
    var f = function() { ; };
    testAutoArray(f);
    var arr = ["", ""];
    var obj = {a:1, b:2};
    testAutoArray(arr);
    testAutoArray(obj);
}

// start to test
testAutoType();
