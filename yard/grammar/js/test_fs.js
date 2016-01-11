var fs = require("fs");

function testReadFile() {
    var file = "/etc/passwd";
    var coding = "utf-8";

    // sync mode
    var txt = fs.readFileSync(file, coding);
    console.log(txt);
    console.log("read file SYNC END!!");

    // async mode
    fs.readFile(file, coding, function(err, data) {
        if (err) {
            console.log("error: " + err);
        } else {
            console.log(data);
        }
    });
    console.log("read file ASYNC END!!");
}

function calcFileMd5ByStream(file) {
    var md5 = "";
    if (!fs.existsSync(file)) {
        console.error("not found the file: " + file);
        return md5;
    }

    var hash = require("crypto").createHash("md5");
    var rs = fs.createReadStream(file);
    rs.on("data", function(data) {
        console.log("read size: " + data.length);
        hash.update(data);
    });
    rs.on("end", function() {
        md5 = hash.digest("hex");
        console.log("read stream END!! md5=" + md5);
    });
    return md5;
}

function calcFileMd5(file) {
    var md5 = "";
    if (!fs.existsSync(file)) {
        console.error("not found the file: " + file);
        return md5;
    }

    var hash = require("crypto").createHash("md5");
    var fd = fs.openSync(file, "r");
    var buf = new Buffer(64 * 1024);
    for (var rd = 0; (rd = fs.readSync(fd, buf, 0, buf.length)) > 0; ) {
        hash.update(buf.slice(0, rd));
        //console.log("read size: " + rd);
    }
    fs.closeSync(fd);
    md5 = hash.digest("hex");
    //console.log("read file END!! md5=" + md5);
    return md5;
}

function testReadBinFile() {
    //var file = "/etc/passwd";
    var file = "/Users/bbxyard/stub/win.soft/cn_windows_10_pro_x64_dvd_6848463.iso";
    var rs = fs.createReadStream(file);
    rs.on("data", function(data) {
        console.log("read size: " + data.length);
    });
    rs.on("end", function() {
        console.log("read stream END!!");
    });
}


function testExists() {
    fs.exists('/etc/passwd', function (exists) {
    	console.log(exists ? "存在" : "不存在!");
    });
}

//testReadFile();
//testReadBinFile();
var md5 = calcFileMd5("/Users/bbxyard/stub/win.soft/cn_windows_10_pro_x64_dvd_6848463.iso");
//var md5 = calcFileMd5("/etc/passwd");
console.log("the big file md5 hash is: " + md5);
testExists();
