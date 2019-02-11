/**
 * @file obj-diff.js
 * @description 对象比较
 */

import BaseHandler from '../com/base';
import {R, G, B, Y, BB, BG, BR, BY, printDebug, logInfo} from '../com/color-output';

class ObjDiffHandler extends BaseHandler {
  constructor() {
    const options = [
      { n: '-s, --same', d: 'output same' },
      { n: '-d, --diff', d: 'output diff' }
    ];
    super('obj-diff <js1> <js2>', '比较两个JS文件中对象', options);
  }

  process(prog, argv) {
    const f1 = argv[0];
    const f2 = argv[1];
    printDebug('INPUT file: <%s> <%s>', B(f1), B(f2));
    const obj1 = require(f1);
    const obj2 = require(f2);
    const objSame = {};
    const objDiff = {};
    for (let key in obj1) {
      if (obj1[key] === obj2[key]) objSame[key] = obj1[key];
      else objDiff[key] = obj1[key];
    }

    if (prog.same) {
      console.log('--SAME: ', objSame);
    }
    if (prog.diff) {
      console.log('--DIFF: ', objDiff);
    }
 }
};

module.exports = ObjDiffHandler;
