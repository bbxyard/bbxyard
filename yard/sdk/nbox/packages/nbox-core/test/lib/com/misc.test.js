import chai from 'chai';
const expect = chai.expect;

const UT = require('../../../index');

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

/**
 * @name 对象判断
 */
describe('Object', function() {
  it('isEmpty', () => {
    expect(UT.isEmpty(null)).to.be.ok;
    expect(UT.isEmpty(undefined)).to.be.ok;
    expect(UT.isEmpty('')).to.be.ok;
    expect(UT.isEmpty({})).to.be.ok;
    expect(UT.isEmpty([])).to.be.ok;
    expect(UT.isEmpty([{}])).to.be.not.ok;
    expect(UT.isEmpty('name')).to.be.not.ok;
    expect(UT.isEmpty({year: 2018})).to.be.not.ok;
    expect(UT.isEmpty([{month: 'June'}])).to.be.not.ok;
  });
  it('isEqual', () => {
    const objA = { name: 'nbox', sub: 5 };
    const objB = { name: 'nbox' };
    expect(UT.isEqual(objA, objB)).to.be.not.ok;
    objB.sub = 5;
    expect(UT.isEqual(objA, objB)).to.be.ok;
    delete objA.sub;
    expect(UT.isEqual(objA, objB)).to.be.not.ok;
  });
});
