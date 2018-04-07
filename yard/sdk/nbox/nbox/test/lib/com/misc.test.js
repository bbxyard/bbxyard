import chai from 'chai';
const expect = chai.expect;

const nbox = require('../../../lib/nbox');
const UT = nbox.com;


/**
 * @name String 区
 */
describe('String', function() {
  it('toString16', () => {
    expect(UT.toString16(100, 4)).to.be.equal('0064');
  });
  it('pad0', () => {
    expect(UT.pad0(2)).to.be.equal('02');
    expect(UT.pad0(20)).to.be.equal('20');
  });
  it('atof2', () => {
    expect(UT.atof2('3.1415')).to.be.equal(3.14);
  });
})

/**
 * @name DateTime测试区
 */
describe('DT', function() {
  it('Format', () => {
    let s = UT.genDataTimeHRStr(new Date());
    UT.debugPrint('now is: ' + s);
  });
});
