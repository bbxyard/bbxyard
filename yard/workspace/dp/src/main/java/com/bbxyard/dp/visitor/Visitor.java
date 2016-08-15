package com.bbxyard.dp.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Visitor {
    public static void main(String[] args) {
        // 定义对象结构
        ObjectStructure os = new ObjectStructure();

        // 挂载被访问元素对象
        os.attach(new ConcreteElementA("e-a"));
        os.attach(new ConcreteElementB("e-b"));

        // 开始友好访问
        os.display(new ConcreteVisitor1("visit1"));
        os.display(new ConcreteVisitor2("visit2"));
        os.display(new ConcreteVisitor2("visit2B"));

        // done
        System.out.println("Visitor.main.done!!");
    }
}

interface IVisitor {
    void visitConcreteElementA(ConcreteElementA a);
    void visitConcreteElementB(ConcreteElementB b);
}

interface Element {
    void accept(IVisitor visitor);
}

class ObjectStructure {
    public ObjectStructure() {
        elements = new LinkedList<Element>();
    }
    public void attach(Element e) {
        elements.add(e);
    }
    public void detach(Element e) {
        elements.remove(e);
    }
    public void display(IVisitor v) {
        for (Element e: elements) {
            e.accept(v);
        }
    }
    private List<Element>   elements;
}

/**
 * 一个命名实体类
 */
class Identity {
    public Identity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String name;
}

class ConcreteVisitor1 extends Identity implements IVisitor  {
    public ConcreteVisitor1(String name) {
        super(name);
    }
    public void visitConcreteElementA(ConcreteElementA a) {
        System.out.printf("ConcreteVisitor1.visitConcreteElementA|[%s] is visited by [%s]!!\n", a.getName(), this.getName());
        a.operatorA();
    }
    public void visitConcreteElementB(ConcreteElementB b) {
        System.out.printf("ConcreteVisitor1.visitConcreteElementB|[%s] is visited by [%s]!!\n", b.getName(), this.getName());
        b.operatorB();
    }
}

class ConcreteVisitor2 extends Identity implements IVisitor {
    public ConcreteVisitor2(String name) {
        super(name);
    }
    public void visitConcreteElementA(ConcreteElementA a) {
        System.out.printf("ConcreteVisitor2.visitConcreteElementA|[%s] is visited by [%s]!!\n", a.getName(), this.getName());
        a.operatorA();
    }
    public void visitConcreteElementB(ConcreteElementB b) {
        System.out.printf("ConcreteVisitor2.visitConcreteElementB|[%s] is visited by [%s]!!\n", b.getName(), this.getName());
        b.operatorB();
    }
}

class ConcreteElementA extends Identity implements Element {
    public ConcreteElementA(String name) {
        super(name);
    }
    public void accept(IVisitor visitor) {
        visitor.visitConcreteElementA(this);
    }
    public void operatorA() {
        System.out.println("ConcreteElementA.operatorA");
    }
}

class ConcreteElementB extends Identity implements Element {
    public ConcreteElementB(String name) {
        super(name);
    }
    public void accept(IVisitor visitor) {
        visitor.visitConcreteElementB(this);
    }
    public void operatorB() {
        System.out.println("ConcreteElementB.operatorB");
    }
}