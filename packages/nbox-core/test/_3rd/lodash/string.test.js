var _ = require('lodash');
var expect = require('chai').expect;


/**
 * Normalize base on case-convert
 * @return {[type]} [description]
 */
describe('Normalize base on case-convert', function() {
  it('normalize by camel', () => {
    const norVal = 'fooBar';
    expect(_.camelCase('Foo Bar')).to.be.equal(norVal);
    expect(_.camelCase('--Foo Bar--')).to.be.equal(norVal);
    expect(_.camelCase('__FOO_BAR__')).to.be.equal(norVal);
  });

  it('normalize by lower', () => {
    const norVal = 'foo bar';
    expect(_.lowerCase('Foo Bar')).to.be.equal(norVal);
    expect(_.lowerCase('--Foo Bar--')).to.be.equal(norVal);
    expect(_.lowerCase('__FOO_BAR__')).to.be.equal(norVal);
  });
});
