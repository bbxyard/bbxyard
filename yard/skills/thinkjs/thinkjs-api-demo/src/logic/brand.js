module.exports = class extends think.Logic {
  indexAction() {

  }

  listAction() {
    this.rules = {
      page: { required: true },
      size: { required: true }
    };
  }

  detailAction() {
    this.rules = {
      id: { required: true }
    };
  }
};
