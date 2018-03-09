module.exports = class extends think.Logic {
  indexAction() {

  }

  listAction() {
    this.rules = { typeId: { required: true } };
  }

  addOrDelAction() {
    this.rules = {
      typeId: { required: true },
      valueId: { required: true }
    };
  }
};
