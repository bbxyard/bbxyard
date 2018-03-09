module.exports = class extends think.Logic {
  indexAction() {
    this.rules = { id: { required: true } };
  }

  currentAction() {
    this.rules = {
      id: { required: true }
    };
  }
};
