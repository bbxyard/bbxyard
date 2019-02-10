var expect = require('chai').expect;


/**
 * equal test
 * @param  {[type]} to [description]
 * @return {[type]}    [description]
 */
describe('Equal', function(){
  it('Equal', function(){
    expect(4 + 5).to.be.equal(9);
  });
  it('to.be.not.equal', function(){
    expect(4 + 5).to.be.not.equal(10);
  });
  it('to.be.deep.equal', function(){
    var foo = { bar: 'baz' };
    expect(foo).to.be.deep.equal({ bar: 'baz' });
  });
});


/**
 * Boolean test
 * @return {[type]} [description]
 */
describe('Boolean', function(){
  it('to.be.ok', function(){
    expect(true).to.be.ok;
  });
  it('to.be.not.ok', function(){
    expect(false).to.be.not.ok;
  });
});


/**
 * typeof
 * @return {[type]} [description]
 */
describe('typeof', function(){
  it('to.be.a', function(){
    expect('test').to.be.a('string');
  });
  it('to.be.an', function(){
    expect({foo: 'bar'}).to.be.an('object');
  });
  it('to.be.an.instanceof', function(){
    function Foo() {};
    var foo = new Foo();
    expect(foo).to.be.an.instanceof(Foo);
  });
});


/**
 * include
 * @return {[type]} [description]
 */
describe('include', function(){
  it('to.include', function(){
    expect([1, 2, 3]).to.include(2);
  });
  it('to.contain', function(){
    expect('foobar').to.contain('bar');
  });
  it('to.include.keys', function(){
    expect({ foo: 'bar', date: '2018-02-20' }).to.include.keys('date');
  });
});


/**
 * Empty
 * @return {[type]} [description]
 */
describe('Empty', function(){
  it('to.be.empty', function(){
    expect('').to.be.empty;
    expect([]).to.be.empty;
    expect({}).to.be.empty;
  });
});


/**
 * Match
 * @return {[type]} [description]
 */
describe('Match', function(){
  it('to.match', function(){
    expect('foobar').to.match(/^foo/);
  })
});
