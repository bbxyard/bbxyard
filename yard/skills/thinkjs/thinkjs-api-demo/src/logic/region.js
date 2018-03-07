module.exports = class extends think.Logic {
  indexAction() {

  }

  infoAction() {
    this.rules = { regionId: { required: true } };
  }

  listAction() {
    this.rules = { parentId: { required: true } };
  }
};
