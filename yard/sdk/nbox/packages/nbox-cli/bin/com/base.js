/**
 * @file base.js
 * @description 所有子命基类.
 */

import {R, G, B, printDebug} from './color-output';

export default class BaseHandler {
  constructor(name, desc, options) {
    this.nbox = require('./api-proxy');
    this.name = name;
    this.desc = desc;
    this.options = options;
  }

  alias(shortName) {
    this.aliasName = shortName;
  }

  process(prog, argv) {
    printDebug('argv: ', G(argv));
    printDebug('prog: ', B(prog));
    printDebug('This is the Default handler');
  }

  render(program) {
    const that = this;
    let subprog = program.command(this.name).description(this.desc);
    if (this.aliasName) {
      subprog = subprog.alias(this.aliasName);
    }
    this.options.forEach(opt => {
      subprog = subprog.option(opt.n, opt.d);
    });
    subprog = subprog.action( function(...args) {
      let prog = args.pop();
      let argv = args;
      that.process(prog, argv);
    });
    return subprog;
  }
};
