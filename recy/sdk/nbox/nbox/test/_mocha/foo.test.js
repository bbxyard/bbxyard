var expect = require('chai').expect;
var foo = require("./foo");
var add = foo.add;
var mult = foo.mult;


/**
 * Add method test
 * @return {[type]} [description]
 */
describe('Add method test', function(){
  it('1 + 1 == 2', function() {
    expect(add(1, 1)).to.be.equal(2);
  });
  it('x + 0 == x', function() {
    expect(add(1, 0)).to.be.equal(1);
  });
});


/**
 * Multiply method test
 * @return {[type]} [description]
 */
describe('Multiply method test', function(){
  it('1 * 1 == 1', function() {
    expect(mult(1, 1)).to.be.equal(1);
  });
});
