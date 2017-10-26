// list object info

function showObjectInfo(obj) {
    var props = "";
    var funcs = "";
    for (var key in obj) {
        var value = obj[key];
        if (typeof(value) ==  "function") {
            funcs += "func: " + key + value + "\n";
        } else {
            props += key + "=" + value + "\t";
        }
    }
    var ret = props + "\n" + funcs;
    console.log(ret);
    return ret;
}

arr = new Array();
arr[0] = "HEY";
arr[1] = "ABC";

str = "hello world";
obj = {
        "name":"boxu",
        "age": 32,
        show: function(){ console.log("hey: " + this.name)},
        run: function(arg1, arg2) {
            return arg1 + arg2;
        }
      };

showObjectInfo(arr);
showObjectInfo(str);
showObjectInfo(obj);
