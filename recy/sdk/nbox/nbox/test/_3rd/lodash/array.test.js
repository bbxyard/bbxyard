var expect = require('chai').expect;
var _ = require('lodash');


/**
 * chunk
 * @return {[type]} [description]
 */
describe('chunk', function(){
  it('chunk', () => {
    let arr = ['a', 'b', 'c', 'd', 'e'];
    expect(_.chunk(arr, 2)).to.be.deep.equal([['a', 'b'], ['c', 'd'], ['e']]);
    expect(_.chunk(arr, 3)).to.be.deep.equal([['a', 'b', 'c'], ['d', 'e']]);
  });
});


/**
 * compact
 * @return {[type]} [description]
 */
describe('compact', function(){
  it('compact', () => {
    let arr = [0, 1, false, 2, '', 3, 4];
    expect(_.compact(arr)).to.be.deep.equal([1, 2, 3, 4]);
  });
});


/**
 * concat
 * @return {[type]} [description]
 */
describe('concat', function(){
  it('concat', () => {
    var arr = [1];
    expect(_.concat(arr, 2, [3], [[4]])).to.be.deep.equal([1, 2, 3, [4]]);
  });
});


/**
 * 分片
 * @return {[type]} [description]
 */
describe('slice', function(){
  let n = [0, 1, 2, 3];
  it('nth', () => {
    expect(_.nth(n, 0)).to.be.deep.equal(0);
    expect(_.nth(n, 1)).to.be.deep.equal(1);
    expect(_.nth(n, 2)).to.be.deep.equal(2);
    expect(_.nth(n, 3)).to.be.deep.equal(3);
  });
  it('tail', () => {
    expect(_.tail(n)).to.be.deep.equal([1, 2, 3]);
    expect(_.nth(n, 3)).to.be.deep.equal(3);
  });
  it('initial', () => {
    expect(_.initial(n)).to.be.deep.equal([0, 1, 2]);
    expect(_.nth(n)).to.be.deep.equal(0);
    expect(_.nth(n, 0)).to.be.deep.equal(0);
  });
});

/**
 * 集合-交并补差-运算
 * @return {[type]} [description]
 */
describe('set', function(){
  it('set-basic', () => {
    var n21 = [2, 1], n23 = [2, 3], n32 = [3, 2];
    expect(_.intersection(n21, n23)).to.be.deep.equal([2]);
    expect(_.union(n21, n23)).to.be.deep.equal([2, 1, 3]);
    expect(_.difference(n21, n23)).to.be.deep.equal([1]);

    expect(_.intersection(n21, n32)).to.be.deep.equal([2]);
    expect(_.union(n21, n32)).to.be.deep.equal([2, 1, 3]);
    expect(_.difference(n21, n32)).to.be.deep.equal([1]);
  });

  it('set-by', () => {
    // 小数取整
    var f11 = [1.2, 2.1, 3.14], f12 = [1.6, 2.4];
    expect(_.intersectionBy(f11, f12, Math.floor)).to.be.deep.equal([1.2, 2.1]);
    expect(_.unionBy(f11, f12, Math.floor)).to.be.deep.equal(f11);
    expect(_.differenceBy(f11, f12, Math.floor)).to.be.deep.equal([3.14]);
    // 对象取属性
    var o11 = [{'x': 2}, {'x': 1}], o12 = [{'x': 3}, {'x': 2}];
    expect(_.intersectionBy(o11, o12, obj => obj.x)).to.be.deep.equal([{'x': 2}]);
    expect(_.unionBy(o11, o12, obj => obj.x)).to.be.deep.equal([{x: 2}, {x: 1}, {x: 3}]);
    expect(_.differenceBy(o11, o12, obj => obj.x)).to.be.deep.equal([{'x': 1}]);
  });

  it('set-with', () => {
    let a = [ {x: 3, y: 4}, {x: 15, y: 8} ];
    let b = [ {x: 3, y: 4}, {x: 6, y: 8} ];
    let c = [ {x: 3, y: 4}, {x: 15, y: 8}, {x: 6, y: 8} ];
    expect(_.intersectionWith(a, b, _.isEqual)).to.be.deep.equal([{x: 3, y: 4}]);
    expect(_.unionWith(a, b, _.isEqual)).to.be.deep.equal(c);
    expect(_.differenceWith(a, b, _.isEqual)).to.be.deep.equal([{x: 15, y: 8}]);
  });


  it('basic-intersection', () => {
    expect(_.intersection([2, 1], [2, 3])).to.be.deep.equal([2]);
  });
  it('basic-union', () => {
    expect(_.union([2, 1], [2, 3])).to.be.deep.equal([2, 1, 3]);
  });
  it('basic-difference', () => {
    expect(_.difference([2, 1], [2, 3])).to.be.deep.equal([1]);
  });
});
