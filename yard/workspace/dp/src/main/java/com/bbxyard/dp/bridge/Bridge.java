package com.bbxyard.dp.bridge;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Bridge {
    public static void main(String[] args) {
        Implementor impA = new ConcreteImplementorA();
        Implementor impB = new ConcreteImplementorB();

        // 先桥接到impA
        Abstraction abstraction = new RefinedAbstraction(impA);
        abstraction.request();

        // 再动态绑定到impB
        abstraction.rebindImplementor(impB);
        abstraction.request();

        System.out.println("Bridge.main.done!!");
    }
}


interface Implementor {
    void operate();
}

abstract class Abstraction {
    public Abstraction(Implementor imp) {
        this.imp = imp;
    }
    public void request() {
        System.out.println("Abstraction.request|where imp=" + this.imp.toString());
        this.imp.operate();
    }
    public void rebindImplementor(Implementor imp) {
        this.imp = imp;
    }
    private Implementor imp;
}

class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor imp) {
        super(imp);
    }
    @Override
    public void request() {
        super.request();
        System.out.println("RefinedAbstraction.request|here add sth special");
    }
}


class ConcreteImplementorA implements Implementor {
    public void operate() {
        System.out.println("ConcreteImplementorA.operate");
    }
}

class ConcreteImplementorB implements Implementor {
    public void operate() {
        System.out.println("ConcreteImplementorB.operate");
    }
}
