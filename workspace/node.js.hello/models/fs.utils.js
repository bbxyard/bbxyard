fs = require("fs");
Path = require("path");

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

function FileInfo(path, size) {
	this.path = path;					// name with path
	this.name = Path.basename(path);	// name only
	this.size = size | 0;	// size
	this.md5  = calcFileMd5(this.path);
}

FileInfo.prototype.info = function() {
	var s = ("path=" + this.path + "; name=" + this.name +
					 + "; size=" + (this.size / 1024).toFixed(2) + "KB"
				 	 + "; md5=" + this.md5);
	return s;
}

function flist2string(flist) {
	var s = "";
	for (var i = 0; i < flist.length; ++i) {
		s += flist[i].info() + "\n";
	}
	return s;
}

/**
 *
 * get file list
 *
 */
function getFileList(path) {
	var flist = [];
	readFile(path, flist);
	return flist;
}

// 遍历读取文件
function readFile(path, flist) {
	files = fs.readdirSync(path);
	files.forEach(walk);
	function walk(file) {
		fpath = path + '/' + file;
		states = fs.statSync(fpath);
		if (states.isDirectory()) {
			readFile(fpath, flist);	// 递归
		} else {
			var f = new FileInfo(fpath, states.size);
			console.log("add: " + f.info());
			flist.push(f);
		}
	}
}

// write utf-8 buffer string to file
function writeFile(file, data) {
	fs.writeFile(file, data, "utf-8", onFinish);
	function onFinish() {
		console.log("file wrote finished!!");
	}
}

// file sort handler
function sortHandlerBySize(lhs, rhs) {
	if (lhs.size > rhs.size) {
		return -1;
	} else if (lhs.size < rhs.size) {
		return 1;
	} else {
		return 0;
	}
}

// demo
function fsUtilsDemo() {
	var flist = getFileList("/Users/bbxyard/stub");
	flist.sort(sortHandlerBySize);
	writeFile("/tmp/stub.lst", flist2string(flist));
	console.log(flist);
}

// just do it!
fsUtilsDemo();
