var expect = require('chai').expect;
import Validate from '../../lib/utils/Validate';


/**
 * 手机号验证
 * @return {[type]} [description]
 */
describe('手机号验证', function(){
  it('13x 11 digits', () => {
    expect(Validate.tel('13581506320')).to.be.ok;
  });
  it('13x +86 not-ok', () => {
    expect(Validate.tel('+8613581506320')).to.be.not.ok;
  });
});


/**
 * email测试
 * @return {[type]} [description]
 */
describe('email测试', function(){
  it('stdandard email', () => {
    expect(Validate.email('bbxyard@gmail.com')).to.be.ok;
  });
});
