// json str array obj convert each otherwise

var pSwiperPackage = [
  { "ad_id": "3", "ad_name": "鸡蛋banner", "enabled": "1", "image": "http://dcstatic.shizhencaiyuan.com/Uploads/2017-09-14/59b9607009109.jpg", "sort": "2", "type": "0", "ad_link": "http://www.baidu.com", "ad_info": "", "goods_id": "25", "images": [] },
  { "ad_id": "6", "ad_name": "荔枝", "enabled": "1", "image": "http://dcstatic.shizhencaiyuan.com/Uploads/2017-05-31/592e28aad4091.jpg", "sort": "0", "type": "1", "ad_link": "", "ad_info": "", "goods_id": "54", "images": [] }
];

function showSwiperPackage(pkg) {
    for (var i = 0; i < pkg.length; ++i) {
        // loop every item
        var item = pkg[i];
        for (var key in item) {
            console.log("(key, value)=(%s, %s)", key, item[key]);
        }
    }
}

showSwiperPackage(pSwiperPackage);
