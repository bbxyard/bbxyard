#! /usr/bin/env node
// demo how to use the process.argv

// print process.argv
process.argv.forEach(function(val, index, array) {
	console.log(index + ": " + val);
});

if (process.getgid) {
  console.log('当前 gid: ' + process.getgid());
}

if (process.getuid) {
  console.log('当前 uid: ' + process.getuid());
}

console.log('cwd: ' + process.cwd());
