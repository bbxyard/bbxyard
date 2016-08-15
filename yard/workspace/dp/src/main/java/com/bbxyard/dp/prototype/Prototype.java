package com.bbxyard.dp.prototype;

/**
 * Created by bbxyard on 16-8-14.
 */
public class Prototype {
    public static void main(String[] args) {
        IPrototype prototypeA = new ConcretePrototypeA("boxu", "20160814");
        IPrototype prototypeB = new ConcretePrototypeB("red");

        IPrototype prototypeA2 = prototypeA.clone();
        IPrototype prototypeB2 = prototypeB.clone();

        System.out.println("original A: " + prototypeA.toString());
        System.out.println("original B: " + prototypeB.toString());
        System.out.println("cloned   A: " + prototypeA2.toString());
        System.out.println("cloned   B: " + prototypeB2.toString());

        System.out.println("Prototype.main.done!");
    }
}

interface IPrototype {
    IPrototype clone();
}

class ConcretePrototypeA implements IPrototype {
    public ConcretePrototypeA(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public IPrototype clone() {
        IPrototype prototype = new ConcretePrototypeA(this.name, this.id);
        return prototype;
    }
    @Override
    public String toString() {
        return super.toString() + " BUT name=" + name + ", id=" + id;
    }
    private String name;
    private String id;
}

class ConcretePrototypeB implements IPrototype {
    public ConcretePrototypeB(String color) {
        this.color = color;
    }
    public IPrototype clone() {
        return new ConcretePrototypeB(this.color);
    }
    @Override
    public String toString() {
        return super.toString() + " BUT color=" + color;
    }
    private String color;
}
