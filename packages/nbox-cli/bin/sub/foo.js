/**
 * @file foo.js
 */

import BaseHandler from '../com/base';
import {R, G, B, Y, BB, BG, BR, BY, printDebug, logInfo} from '../com/color-output';

class Foo extends BaseHandler {
  constructor() {
    const options = [
      { n: '-s, --src <dir>', d: 'src dir' },
      { n: '-d, --dst <outfile>', d: 'destion file' },
      { n: '-l, --list', d: 'list files' }
    ];
    super('foo', 'demo-foo', options);
  }

  process(prog, argv) {
    logInfo(G('src: %s'), B(prog.src));
    logInfo(Y('dst: %s'), R(prog.dst));
    logInfo(BR('argv: ') + G(argv));
    printDebug('I am from the FOO handler: (src, dst)=(%s, %s)', prog.src, prog.dst);
  }
};

module.exports = Foo;
