module.exports = class extends think.Logic {
  indexAction() {

  }

  detailAction() {
    this.rules = {
      id: { required: true }
    };
  }
};
