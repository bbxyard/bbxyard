module.exports = class extends think.Logic {
  indexAction() {

  }

  countAction() {
    this.rules = {
      typeId: { required: true },
      valueId: { required: true }
    };
  }

  listAction() {
    this.rules = {
      typeId: { required: true },
      valueId: { required: true }
    };
  }
};
