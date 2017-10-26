// test API setTimeout setInterval;

function testSetInterval(cnt) {
  var n = cnt;
  var tid = setInterval(function(){
    if (--n == 0)
      clearInterval(tid);
    console.log("hello setInterval %d/%d", (cnt - n), cnt);
  }, 1000);
}

function testSetTimeout(sec) {
  console.log("setTimeout after %d second start", sec);
  //setTimeout(testSetInterval(8), 5 * 1000);
  setTimeout(function(){
    console.log("ready to go!!");
    testSetInterval(8);
  }, 3000);
  console.log("I'll pre show first!");
}

testSetTimeout(6);
//testSetInterval(10);
