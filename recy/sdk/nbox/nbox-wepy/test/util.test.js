var nbox = require('../index');
var chai = require('chai');
var expect = chai.expect;


/**
 * util test
 * @return {[type]} [description]
 */
describe('util', function() {
  it('formatTime', () => {
    var sdt = nbox.util.formatTime(new Date());
    console.log(sdt);
  });
});
