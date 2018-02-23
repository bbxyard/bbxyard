import nbox from '../index';

let rc = nbox.rcolor;


/**
 * randomcolor test
 * @return {[type]} [description]
 */
describe('randomcolor', function(){
  it('color', () => {
    let c1 = rc();
    let c2 = rc();
    console.log('colors: ', c1, c2);
  });
});
