function calcHash(content, hashAlgName) {
    var crypto = require('crypto');
    var hash = crypto.createHash(hashAlgName);
    hash.update(content);
    var d = hash.digest("hex");
    return d;
}

function calcMD5(content) {
    var d = calcHash(content, "md5");
    return d;
}
function calcSHA1(content) {
    var d = calcHash(content, "sha1");
    return d;
}

console.log("md5  is: " + calcMD5("passwd"));
console.log("sha1 is: " + calcSHA1("passwd"));
