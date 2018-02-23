var expect = require('chai').expect;
var request = require('superagent');
var fetch = require('node-fetch');


let testUrl = 'https://api.github.com';

/**
 * 异步测试
 * @return {[type]} [description]
 */
describe('异步测试', function() {
  it('异步耗时-测试应该5000毫秒后结束', (done) => {
    var x = true;
    var f = function() {
      x = false;
      expect(x).to.be.not.ok;
      done(); // 通知Mocha测试结束
    };
    setTimeout(f, 2500);
  });

  it('异步请求应该返回一个对象', (done) => {
    request
      .get(testUrl)
      .end(function(err, res){
        expect(res).to.be.an('object');
        done();
      });
  });

  it.skip('异步请求应该返回一个对象', (done) => {
    return
      fetch(testUrl).then(function(res){
        console.info(res);
        return res.json();
      }).then(function(json) {
        expect(json).to.be.an('object');
        done();
      });
  });
});


/**
 * Hook测试
 * @return {[type]} [description]
 */
describe('hook', function() {
  before( () => {
    console.log('hook-before all');
  });
  after( () => {
    console.log('hook-after all');
  });
  beforeEach( () => {
    console.log('hook-beforeEach one');
  });
  afterEach( () => {
    console.log('hook-afterEach one');
  });

  it('hook-case1', () => {
    console.log('hook-case1 done');
  });
  it('hook-case2', () => {
    console.log('hook-case2 done');
  });
});

/**
 * hook-sync
 * @return {[type]} [description]
 */
describe('hook-sync', function() {
  var foo = false;
  beforeEach( () => {
    foo = true;
  });
  it('should be changed', () => {
    expect(foo).to.be.ok;
  });
});

/**
 * hook-async
 * @return {[type]} [description]
 */
describe('hook-async', function() {
  var foo = false;
  beforeEach( (done) => {
    setTimeout( () => {
      foo = true;
      done();
    }, 50);
  });
  it('should be changed', () => {
    expect(foo).to.be.ok;
  });
});
