import chai from 'chai';
const expect = chai.expect;

class A {
  static SMA(param) {
    return this.SMB(param);
  }
  static SMB(param) {
    console.log('I am the Class Method of A: ', param);
  }
  methodA(param) {
    console.log('I am the method of A\'s instance: ', param);
  }
};

class ASon extends A {
  static SMSonA(param) {
    return super.SMA(param);
  }
}

class B {
  constructor() {
    this.a = new A();
    this.sayHallo = this.a.methodA.bind(this.a);
  }
};


describe('bind-demo', () => {
  it('bind-this', () => {
    ASon.SMSonA({name: 'static son', desc: 'test-static-son'});
    const b = new B();
    b.sayHallo({name: 'bind A', desc: 'bind test'});
  });
});
