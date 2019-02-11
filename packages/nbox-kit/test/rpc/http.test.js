/**
 * @file http.test.js
 * @description http test
 */

import kit from '../../index';
import chai from 'chai';
const expect = chai.expect;


describe('http', () => {
  it('UrlDownload', () => {
    //UrlUpload('http://weibo.com/', '/tmp/bigfile.pdf', UrlOnError);
    kit.UrlDownload('http://www.cnblogs.com/fengmk2/archive/2011/08/16/2140460.html', '/tmp/downfile.html', kit.UrlOnError);
    console.log('done');
  });
});
