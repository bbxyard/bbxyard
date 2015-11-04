fs = require("fs");

fs.exists('/etc/passwd', function (exists) {
	console.log(exists ? "存在" : "不存在!");
});

