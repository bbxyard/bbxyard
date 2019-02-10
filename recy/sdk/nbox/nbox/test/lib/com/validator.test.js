const expect = require('chai').expect;
const nbox = require('../../../lib/nbox');
const UT = nbox.com;
const Validator = UT.Validator;


/**
 * 手机号验证
 * @return {[type]} [description]
 */
describe('手机号验证', function(){
  it('13x 11 digits', () => {
    expect(Validator.tel('13581506320')).to.be.ok;
  });
  it('13x +86 not-ok', () => {
    expect(Validator.tel('+8613581506320')).to.be.not.ok;
  });
});


/**
 * email测试
 * @return {[type]} [description]
 */
describe('email测试', function(){
  it('stdandard email', () => {
    expect(Validator.email('bbxyard@gmail.com')).to.be.ok;
  });
});
