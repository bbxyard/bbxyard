import chai from 'chai';
let expect = chai.expect;

import {sprintf, vsprintf} from 'sprintf-js';



class MyLogger {
  write(...args) {
    console.log(...args);
  }
  write2(prompt, ...args) {
    let s = vsprintf(prompt, args);
    console.log(s);
  }
  write3(level, prompt, ...args) {
    let s = level + vsprintf(prompt, args);
    console.log(s);
  }
};

/**
 * @name debugPrint
 * @param {any} args
 * @description print to console when needing. 
 */
function debugPrint(...args) {
  console.log(...args);
}

const ME = { name: 'xb', gender: 'male', age: 32 };

/**
 * @name sprintf-js
 * @description sprintf and vsprintf test
 */
describe('sprintf-js', function() {
  it('sprintf-basic', () => {
    let s = sprintf('Hallo %s and %s', 'world', 'JS');
    expect(s).to.be.equal('Hallo world and JS');
    debugPrint(s);
  });
  it('sprintf-object', () => {
    let s = sprintf('Hallo %s: %j', 'Object', ME);
    debugPrint(s);
  });
  it('sprintf-Named-arguments', () => {
    let s = sprintf('My name is: %(name)s', ME);
    debugPrint(s);
  });
  it('sprintf-Argument-swapping', () => {
    let ps = sprintf('%2$s %3$s a %1$s', '1st', '2nd', '3rd');
    expect(ps).to.be.equal('2nd 3rd a 1st');
    debugPrint(ps);
  });
  it('vsprintf', () => {
    let s = vsprintf('[vsprintf] Show: %s %s %s %s', ['a', 'b', 'c', 'd']);
    expect(s).to.be.equal('[vsprintf] Show: a b c d');
    debugPrint(s);
  });
  it('vsprintf', () => {
    let s = vsprintf('', ['A', 'B', 'C']);
    debugPrint(s);
  });
});

/**
 * @name VarArg
 */
describe('VarArg', function() {
  it('Logger', () => {
    let logger = new MyLogger();
    logger.write('Just do it.');
    logger.write('Do you know China: %s', 'IT');
    logger.write('Show %s: ', 'Object', ME);
    console.log('Awesome, You made it.');
  });
  it('Logger-vsprintf', () => {
    let logger = new MyLogger();
    logger.write2('China is %s and %s', 'buautiful', 'awesome');
    logger.write3('debug', 'I like %s, %s and %s', 'A', 'B', 'C');
  });
});
