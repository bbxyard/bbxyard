module.exports = class extends think.Service {
  async xxx() {
    const data = await this.getDataFromApi(); // 这个访问为 extend/service.js 里扩展的方法
    return data;
  }
};
