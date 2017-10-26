// test url decode and encoded

function testUrlcode() {
    var url = "&lt;p&gt;&lt;img src=&quot;http://dcstatic.shizhencaiyuan.com/Uploads/2016-12-07/58479b8e645c2.jpeg&quot; alt=&quot;&quot; style=&quot;max-width:100%;&quot;&gt;&lt;/p&gt;";
    var deurl = decodeURI(url);
    var enurl = encodeURI(deurl);
    console.log("decode: " + deurl);
    console.log("encode: " + enurl);
}

testUrlcode();
