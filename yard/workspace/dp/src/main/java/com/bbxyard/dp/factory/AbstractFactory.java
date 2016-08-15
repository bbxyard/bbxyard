package com.bbxyard.dp.factory;

/**
 * Created by bbxyard on 16-8-14.
 */
public class AbstractFactory {
    public static void main(String[] args) {
        IAbstractFactory factoryX = new ConcreteFactoryX();
        System.out.println("from factory X product A is: " + factoryX.createProductA().name());
        System.out.println("from factory X product B is: " + factoryX.createProductB().name());

        IAbstractFactory factoryY = new ConcreteFactoryY();
        System.out.println("from factory Y product A is: " + factoryY.createProductA().name());
        System.out.println("from factory Y product B is: " + factoryY.createProductB().name());

        System.out.println("AbstractFactory.main.done");
    }
}

class Client {
    public Client() {
    }
}

interface IAbstractProductA {
    String name();
}
interface IAbstractProductB {
    String name();
}
interface IAbstractFactory {
    IAbstractProductA createProductA();
    IAbstractProductB createProductB();
}


class ConcreteFactoryX implements IAbstractFactory {
    public IAbstractProductA createProductA() {
        return new ConcreteProductA1();
    }
    public IAbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}
class ConcreteFactoryY implements IAbstractFactory {
    public IAbstractProductA createProductA() {
        return new ConcreteProductA2();
    }
    public IAbstractProductB createProductB() {
        return new ConcreteProductB2();
    }
}


class ConcreteProductA1 implements IAbstractProductA {
    public String name() {
        return getClass().getName();
    }
}
class ConcreteProductA2 implements IAbstractProductA {
    public String name() {
        return getClass().getName();
    }
}
class ConcreteProductB1 implements IAbstractProductB {
    public String name() {
        return getClass().getName();
    }
}
class ConcreteProductB2 implements IAbstractProductB {
    public String name() {
        return getClass().getName();
    }
}
