package com.bbxyard.dp.factory_method;

/**
 * Created by bbxyard on 16-8-16.
 */
public class FactoryMethod {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Creator creatorB = new ConcreteCreatorB();
        Product productA = creatorA.factoryMethod();
        Product productB = creatorB.factoryMethod();

        System.out.println("FactoryMethod.main | [A] hallo --> " + productA.transform("hallo"));
        System.out.println("FactoryMethod.main | [B] hallo --> " + productB.transform("hallo"));

        System.out.println("FactoryMethod.main | [A] hello --> " + productA.transform("hello"));
        System.out.println("FactoryMethod.main | [B] hello --> " + productB.transform("hello"));

        System.out.println("FactoryMethod.main.done!!");
    }
}

abstract class Creator {
    public abstract Product factoryMethod();
}

abstract class Product {
    public abstract String transform(String s);
}


/**
 * 产品A及对应工厂
 */
class ConcreteProductA extends Product {
    public ConcreteProductA() {
        System.out.println("ConcreteProductA.ConcreteProduct.created!!");
    }
    @Override
    public String transform(String s) {
        String out = s + "--hacked!!";
        return out;
    }
}

class ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}


/**
 * 产品B及对应工厂
 */
class ConcreteProductB extends Product {
    public ConcreteProductB() {
        System.out.println("ConcreteProductB.ConcreteProductB.created!!");
    }
    @Override
    public String transform(String s) {
        return s.toUpperCase();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
