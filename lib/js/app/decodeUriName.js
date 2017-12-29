#!/usr/bin/env node
// rename UriDecodedFileName to NormalFileName


fs = require("fs");
Path = require("path");

function UriDecode(uri) {
    var x = decodeURI(uri);
    return x;
}

function showUsage() {
    var jsfname = Path.basename(process.argv[1]);
    console.log("Usage: node %s <uriDecodedFileName>", jsfname);
}

function main(argv) {
    if (argv.length < 1) {
        showUsage();
        return 1;
    }
    var uriDecodedFileName = argv[0];
    var normalName = UriDecode(uriDecodedFileName);
    // 如果存在则转换，否则仅转换字符串
    if (fs.existsSync(uriDecodedFileName)) {
        console.log("start to rename file %s --> %s", uriDecodedFileName, normalName);
        fs.renameSync(uriDecodedFileName, normalName);
        console.log("done!");
    } else {
        console.log(normalName);
    }

}

// console.log(UriDecode("%E5%AD%A6%E4%BD%8D%E8%AE%BA%E6%96%87+ (副本)"));
main(process.argv.slice(2));
