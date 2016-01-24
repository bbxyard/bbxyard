#! /usr/bin/env node
// demo how to use the process.argv
// demo show the process infomation


// command args
process.argv.forEach(function(val, index, array) {
	console.log(index + ": " + val);
});


// process infomation
console.log('当前 gid: ' + process.getgid());
console.log('当前 uid: ' + process.getuid());
console.log('工作路径: ' + process.cwd());
