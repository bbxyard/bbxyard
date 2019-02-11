import chai from 'chai';
import { resolve } from 'path';
const expect = chai.expect;


function chainCallSub() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      console.log('chain-sub-1st: invoked me after 3secs.');
      resolve('3s-task-done');
    }, 3000);
  }).then((res) => {
    console.log('chain-sub-2nd, receive: ', res);
    return '3s-task-2nd-done';
  }).then((res) => {
    setTimeout(() => {
      console.log('chain-sub-3rd, receive: ', res);
      return '3s-task-3rd-done';
    }, 2000);
  }).then((res) => {
    console.log('chain-sub-4th, receive: ', res);
    return '3s+2s-task-4th-done';
  });
}

function chainCall() {
  console.log('chain-call: begin');
  chainCallSub().then((res) => {
    console.log('chai-call: result: ', res);
  });
  console.log('chain-call: end');
}

describe('promise-test', () => {
  it('chain-test', () => {
    chainCall();
  });
});
