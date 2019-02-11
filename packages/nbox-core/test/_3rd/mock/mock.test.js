const expect = require('chai').expect;
const Mock = require('mockjs');

function debugPrint(data) {
  console.log(JSON.stringify(data, null, 2));
}

const URLS = [ 
  'https://www.bbxyard.com',
  'https://www.xubo.org.cn',
  'https://note.bbxyard.com',
  'https://blog.bbxyard.com',
  'https://d.bbxyard.com',
  'https://d.wx.bbxyard.com',
  'https://www.yvhai.com'
];

function genRandomCntArr(urls) {
  // array to Object.
  const obj = {};
  for (let i = 0; i < urls.length; ++i) {
    obj['id-' + i] = urls[i]; 
  }
  // Mock
  const partObj = Mock.mock({ 'list|2-5': obj }).list;
  const outArray = Object.keys(partObj).map(id => partObj[id]);
  // debugPrint(outArray);
  return outArray;
}

genRandomCntArr(URLS);

describe('basic', () => {
  it('basic', () => {
    const data = Mock.mock({
      'list|1-10': [{
        'id|+1': 1,
        'rate|3-7': '★',
        'km|3-5.1-3': 2,
        // 'pass|1-2': true,
        'object|2-4': {
          '310000': '上海市',
          '320000': '江苏省',
          '330000': '浙江省',
          '340000': '安徽省'
        }
      }]
    });
    // debugPrint(data);
  });
});

describe('array', () => {
  it('+1', () => {
    const data = Mock.mock({
      'list|2-4': [{
        'urls|+1': URLS
      }]
    });
    // debugPrint(data);
  });
  it('mat', () => {
    const data = Mock.mock({
      'mat|2-5': [
        [{'urls|2': URLS}]
      ]
    });
    // debugPrint(data);
  });
});

describe('RegExp', () => {
  it('basic', () => {
    const data = Mock.mock({
      'cars|3-7': [{
        'num': /[A-Z][0-9A-Z][0-9A-Z][0-9A-Z][0-9A-Z]/,
        'photos': () => { return genRandomCntArr(URLS); }
      }]
    });
    debugPrint(data);
  });
});
