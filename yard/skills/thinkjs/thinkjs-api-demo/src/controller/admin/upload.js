const Base = require('../base.js');
const fs = require('fs');

module.exports = class extends Base {
  indexAction() {
    return this.success({path: 'admin/upload'});
  }

  async brandPicAction() {
    const brandFile = this.file('brand_pic');
    if (think.isEmpty(brandFile)) {
      return this.fail('保存失败');
    }
    const that = this;
    const fname = '/static/upload/brand/' + think.uuid(32) + '.jpg';
    const is = fs.createReadStream(brandFile.path);
    const os = fs.createWriteStream(think.ROOT_PATH + '/www' + fname);
    is.pipe(os);
    return that.success({
      name: 'brand_pic',
      fileUrl: 'http://127.0.0.1:8360' + filename
    });
  }

  async brandNewPicAction() {
    const brandFile = this.file('brand_new_pic');
    if (think.isEmpty(brandFile)) {
      return this.fail('保存失败');
    }
    const that = this;
    const fname = '/static/upload/brand/' + think.uuid(32) + '.jpg';
    const is = fs.createReadStream(brandFile);
    const os = fs.createWriteStream(think.ROOT_PATH + '/www' + fname);
    is.pipe(os);

    return that.success({
      name: 'brand_new_pic',
      fileUrl: 'http://127.0.0.1:8360' + fname
    });
  }

  async categoryWapBannerPicAction() {
    const imgFile = this.file('wap_banner_pic');
    if (think.isEmpty(imgFile)) {
      return this.fail('保存失败');
    }
    const that = this;
    const fname = '/static/upload/category/' + think.uuid(32) + '.jpg';

    const is = fs.createReadStream(imgFile);
    const os = fs.createWriteStream(think.ROOT_PATH + '/www' + fname);
    is.pipe(os);

    return that.success({
      name: 'wap_banner_url',
      fileUrl: 'http://127.0.0.1:8360' + fname
    });
  }

  async topicThumbAction() {
    const imageFile = this.file('scene_pic_url');
    if (think.isEmpty(imageFile)) {
      return this.fail('保存失败');
    }
    const that = this;
    const filename = '/static/upload/topic/' + think.uuid(32) + '.jpg';

    const is = fs.createReadStream(imageFile.path);
    const os = fs.createWriteStream(think.ROOT_PATH + '/www' + filename);
    is.pipe(os);

    return that.success({
      name: 'scene_pic_url',
      fileUrl: 'http://127.0.0.1:8360' + filename
    });
  }
};
