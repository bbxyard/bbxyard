var dns = require('dns');
var options = {all: true};

dns.resolve4('id.qq.com', function(err, address){
  if(err) throw err;
  console.log( JSON.stringify(address) );
});


dns.lookup('www.qq.com', function(err, address, family){
  if(err) throw err;
  console.log('例子A: ' + address);
});

dns.lookup('www.qq.com', options, function(err, address, family){
    if(err) throw err;
    console.log('例子B: ' + address);
});
