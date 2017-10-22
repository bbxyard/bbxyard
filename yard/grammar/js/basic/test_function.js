// test function

function func(arg1, arg2, onSucc, onFail) {
    if (arg1 != null) console.log("arg1 = " + arg1);
    if (arg2 != null) console.log("arg2 = " + arg2);
    var res = "function responsed data";
    if (onSucc != null) {
        console.log("execute success callback:");
        onSucc(res);
    }
    if (onFail != null) {
        console.log("execute FAIL callback");
        onFail(res);
    }
}

function funcByJson(obj) {
    var foo = obj.foo;
    var bar = obj.bar;
    var onSucc = obj.onSucc;
    var onFail = obj.onFail;
    if (foo != null) console.log("Recv foo: " + obj.foo);
    if (bar != null) console.log("Recv bar: " + obj.bar);
    if (onSucc != null) onSucc("OK");
    if (onFail != null) onFail("FAIL");
}

func("foo", "bar", function(res){
    console.log("SUCC: " + res);
});

funcByJson({
    "foo": "FOO",
    //bar: "BAR",
    onSucc: function(res) {
        console.log("So cool! " + res);
    }
});
