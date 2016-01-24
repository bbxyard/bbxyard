var buf = new Buffer(1024);
buf.fill(555);
for (var i = 0; i < buf.length; ++i) {
    console.log("index %d is: %s", i, buf[i]);
}
