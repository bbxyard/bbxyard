/**
 * @file foo.js
 * @description boolean型参数测试
 */

import BaseHandler from '../com/base';
import {R, G, B, Y, BB, BG, BR, BY, printDebug, logError, logInfo} from '../com/color-output';

class Bar extends BaseHandler {
  constructor() {
    const options = [
      { n: '-i, --src <dir>?', d: 'src dir' },
      { n: '-d, --debug', d: 'debug mode' },
      { n: '-f, --force', d: 'force mode' }
    ];
    super('bar <arg1> <argB>', 'demo-bar', options);
  }

  process(prog, argv) {
    // 如果参数不足，会上层过滤掉，这里就不用判断啦.
    const arg1 = argv[0];
    const argB = argv[1];
    logError('Args: (arg1, argB) = (%s, %s)', G(arg1), Y(argB));
    if (prog.debug) printDebug(R('This is debug mode') + ' Awesome.');
    if (prog.force) logInfo(B('This is FORCE mode'));
    printDebug('I am from the BAR handler.');
  }
};

module.exports = Bar;
