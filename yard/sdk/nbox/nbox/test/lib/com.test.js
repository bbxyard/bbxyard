var chai = require('chai');
var expect = chai.expect;
var nbox = require('../../lib/nbox');
var com = nbox.com;

// nbox.help();

describe('com', function() {
  it('show', () => {
    console.log('com: ', com);
    expect(true).to.be.ok;
  });
});
