import chai from 'chai';
const expect = chai.expect;

const misc = require('../../../src/com/misc');

describe('misc', function() {
  it('date', () => {
    misc.debugPrint(misc.genDataTimeHRStr());
    expect(true).to.be.ok;
  });
});
