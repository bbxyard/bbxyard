import chai from 'chai';
let expect = chai.expect;


/**
 * number convert
 * @return {[type]} [description]
 */
describe('number-convert', function(){
  it('floor', () => {
    // 向中靠近
    expect(Math.floor(5.4)).to.be.equal(5);
    expect(Math.floor(5.9)).to.be.equal(5);
    expect(Math.floor(-5.5)).to.be.equal(-6);
  });
  it('Math.round', () => {
    // 四舍五入
    expect(Math.round(5.4)).to.be.equal(5);
    expect(Math.round(5.9)).to.be.equal(6);
    expect(Math.round(-5.4)).to.be.equal(-5);
    expect(Math.round(-5.9)).to.be.equal(-6);
  });
});


/**
 * string parse
 * @return {[type]} [description]
 */
describe('string-parse', function(){
  it('ParseInt', () => {
    expect(parseInt("314", 0)).to.be.equal(314);
  });
  it('parseFloat', () => {
    expect(parseFloat('3.14')).to.be.equal(3.14);
  });
});
