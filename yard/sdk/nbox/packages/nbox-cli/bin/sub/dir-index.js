/**
 * @file dir-index.js
 */

import BaseHandler from '../com/base';
import {R, G, B, Y, BB, BG, BR, BY, printDebug, logCrit, logError, logInfo} from '../com/color-output';

class DirIndex extends BaseHandler {
  constructor() {
    const options = [
      { n: '-n, --new', d: 'new or rebuild index' },
      { n: '-c, --compare', d: 'compare index [reserved]' },
    ];
    super('dir-index <inDir> <outFile>', '目录索引化', options);
    this.alias('di');
  }

  process(prog, argv) {
    const srcDir = argv[0];
    const dstFile = argv[1];
    printDebug('IN  srcDir: %s', B(srcDir));
    printDebug('Out dstFile: %s', B(dstFile));
    if (prog['new']) {
      let ret = this.nbox.kit.calcDirMd5(srcDir, dstFile);
      logCrit(G('calcDirMd5: <%s> -> <%s> done.'), srcDir, dstFile);
    } else if (prog.compare) {
      logError(G('compare: <%s> -> <%s> NOT IMPL.'), srcDir, dstFile);
    }

  }
};

module.exports = DirIndex;
