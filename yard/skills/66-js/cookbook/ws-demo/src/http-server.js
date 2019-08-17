const app = require('express')();
const server = require('http').Server(app);

app.get('/', (req, resp)=> {
  resp.sendfile(__dirname + "/test.html");
});

app.listen(3000);
