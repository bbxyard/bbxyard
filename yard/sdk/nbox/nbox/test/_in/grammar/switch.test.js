import chai from 'chai';
const expect = chai.expect;


function num2strDay(n) {
  let sday = 'unknow';
  switch (n % 7) {
    case 1:
      sday = 'Mon';
      break;
    case 2:
      sday = 'Tue';
      break;
    case 3:
      sday = 'Wend'
      break;
  }
  return sday;
}

function en2cn(sday) {
  let res = '不定';
  switch (sday) {
    case 'Mon':
      res = '星期一';
      break;
    case 'Tue':
      res = '星期二';
      break;
    case 'Wend':
      res = '星期三'
      break;
  }
  return res;
}

/**
 * switch test
 * @return {[type]} [description]
 */
describe('switch', function(){
  it('switch-num', () => {
    expect(num2strDay(8)).to.be.equal('Mon');
    expect(num2strDay(2)).to.be.equal('Tue');
  });

  it('switch-str', () => {
    expect(en2cn('Mon')).to.be.equal('星期一');
    expect(en2cn('Tue')).to.be.equal('星期二');
  });
});
