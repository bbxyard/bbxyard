const app = require('express')();
const server = require('http').Server(app);
const ws = require("ws");

const ws_server = new ws.Server({port: 8181});

ws_server.on('connection', function connection(ws){
  console.log("server: recv connection.");

  ws.on('message', function incoming(message){
    console.log('server: recv %s', message);
    ws.send('server: reply');
  });

  ws.on('pong', () => {
    console.log('server: recv pong from client');
  });

  ws.send('Enjoy it');
});

app.listen(4000);
