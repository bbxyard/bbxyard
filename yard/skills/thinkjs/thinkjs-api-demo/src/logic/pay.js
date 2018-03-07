module.exports = class extends think.Logic {
  indexAction() {

  }
  prepayAction() {
    this.rules = { orderId: { required: true } };
  }
};
