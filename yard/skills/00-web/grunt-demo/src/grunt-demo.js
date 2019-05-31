fs = require("fs");
Path = require("path");


/**
 * 计算文件md5, 支持大文件
 */
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


/**
 * 文件信息类
 */
function FileInfo(path, states) {
	this.path = path;					// name with path
	this.name = Path.basename(path);	// name only
	this.size = 0;	// size
    if (null != states) {
        this.size = states.size;
        this.mtime = states.mtime;
    }
	this._id  = calcFileMd5(this.path);
}
FileInfo.prototype.info = function() {
	var s = ("path=" + this.path
                     + "; name=" + this.name
					 + "; size=" + (this.size / 1024).toFixed(2) + "KB"
				 	 + "; md5=" + this._id
                     + "; mtime=" + this.mtime);
	return s;
}
FileInfo.prototype.toString = function() {
    var s = JSON.stringify(this);
    return s;
}


/**
 * 列表序列化到string
 */
function flist2string(flist) {
	var s = "";
	for (var i = 0; i < flist.length; ++i) {
		s += flist[i].toString() + "\n";
	}
	return s;
}

/**
 * get file list
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
		fpath = Path.join(path, file);
        try {
            states = fs.statSync(fpath);
    		if (states.isDirectory()) {
    			readFile(fpath, flist);	// 递归
    		} else {
    			var f = new FileInfo(fpath, states);
                console.log("add: " + f.toString());
    			flist.push(f);
    		}
        } catch (e) {
            console.error("error excption: %s - %s", fpath, e.message);
        }
	}
}

// write utf-8 buffer string to file
function writeFile(file, data) {
	fs.writeFileSync(file, data, "utf-8");
    console.log("file wrote finished!!");
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


function run(indir, outfile) {
	var flist = getFileList(indir);
	flist.sort(sortHandlerBySize);
	writeFile(outfile, flist2string(flist));
	//console.log(flist);
    console.log("total process time: " + process.uptime());
    return 0;
}

function showUsage() {
    var jsname = Path.basename(process.argv[1]);
    console.log("Usage: node %s <indir> <outfile>", jsname);
}


/**
 * main入口
 */
function main(argv) {
    if (argv.length < 2) {
        showUsage();
        return 2;
    }
    var indir = argv[0];
    var outfile = argv[1];
    console.log("indir=%s\noutfile=%s", indir, outfile);
    var ret = run(indir, outfile);
    process.exit(ret);
}


// just do it!
// run("/Users/bbxyard/stub", "/tmp/stub.lst");
main(process.argv.slice(2));
