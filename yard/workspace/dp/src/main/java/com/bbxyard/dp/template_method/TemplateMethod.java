package com.bbxyard.dp.template_method;

/**
 * Created by bbxyard on 16-8-15.
 */
public class TemplateMethod {
    public static void main(String[] args) {
        AbstractClass obj = new ConcreteClass();
        obj.run();
        System.out.println("TemplateMethod.main.done!!");
    }
}


abstract class AbstractClass {
    public void run() {
        System.out.println("AbstractClass.run.begin");
        System.out.println("AbstractClass.run | invoke primitive-1 begin");
        primitiveOperation1();
        System.out.println("AbstractClass.run | invoke primitive-1 done");
        System.out.println("AbstractClass.run | invoke primitive-2 BEGIN");
        primitiveOperation2();
        System.out.println("AbstractClass.run | invoke primitive-2 DONE");
        System.out.println("AbstractClass.run.done");
    }

    abstract void primitiveOperation1();
    abstract void primitiveOperation2();
}

class ConcreteClass extends AbstractClass {
    @Override
    void primitiveOperation1() {
        System.out.println("==> ConcreteClass.primitiveOperation1");
    }
    @Override
    void primitiveOperation2() {
        System.out.println("==> ConcreteClass.primitiveOperation2");
    }
}