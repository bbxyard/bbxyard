
function matchOne(x, y, z) {
  // N 数字OK; POS 位置OK; NP: 都OK
  const ruleList = [
    { v: 682, N: 1, NP: 1, POS: 1 },
    { v: 614, N: 1, NP: 0, POS: 0 },
    { v: 206, N: 2, NP: 0, POS: 0 },
    { v: 738, N: 0, NP: 0, POS: 0 },
    { v: 870, N: 1, NP: 0, POS: 0 },
  ];
  const res = { passCnt: 0 };
  const testNumList = [x, y, z];
  for (let i = 0; i < ruleList.length; ++i) {
    const {v, N, NP, POS} = ruleList[i];
    const ruleNumList = [parseInt(v / 100), parseInt(v / 10 % 10), v % 10];
    const _res = { N: 0, NP: 0, POS: 0 };
    for (let j = 0; j < ruleNumList.length; ++j) {
      // 是否完全匹配?
      const pos = testNumList.findIndex(n => n === ruleNumList[j]);
      if (pos >= 0) {
        _res.N += 1;  // 数字OK
        if (pos === j) {  // 位置也OK
          _res.NP += 1;
          _res.POS += 1;
        }
      }
    }
    if (_res.N === N && _res.NP === NP && _res.POS === POS) res.passCnt += 1;
  }
  res.ok = (res.passCnt === ruleList.length);
  return res;
}

function match() {
  for (let x = 0; x < 10; ++x) {
    for (let y = 0; y < 10; ++y) {
      for (let z = 0; z < 10; ++z) {
        if (x === y || y === z || x === z)
          continue;
        const res = matchOne(x, y, z);
        if (res.passCnt >= 4)
          console.log('try to match: %d%d%d PASS=%d [%s]', x, y, z, res.passCnt, res.ok ? 'YES' : 'NO' );
        if (res.ok) {
          console.log('result is: ', x, y, z);
        }
      }
    }
  }
}

match();
