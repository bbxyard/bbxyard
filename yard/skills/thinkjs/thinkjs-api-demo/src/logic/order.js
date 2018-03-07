module.exports = class extends think.Logic {
  indexAction() {

  }

  expressAction() {
    this.rules = {
      orderId: { required: true }
    };
  }
};
