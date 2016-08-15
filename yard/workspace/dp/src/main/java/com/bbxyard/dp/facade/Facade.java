package com.bbxyard.dp.facade;

/**
 * Created by bbxyard on 16-8-12.
 */
public class Facade {

    public Facade() {
        ss1 = new SubSystemOne();
        ss2 = new SubSystemTwo();
        ss3 = new SubSystemThree();
        ss4 = new SubSystemFour();
    }

    public  void methodA() {
        System.out.println("Facade.methodA");
        ss1.methodOne();
        ss3.methodThree();
        ss4.methodFour();
    }

    public  void methodB() {
        System.out.println("Facade.methodB");
        ss2.methodTwo();
        ss4.methodFour();
        ss1.methodOne();
    }

    private SubSystemOne    ss1;
    private SubSystemTwo    ss2;
    private SubSystemThree  ss3;
    private SubSystemFour   ss4;

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }
}

class SubSystemOne {
    public void methodOne() {
        System.out.println("SubSystemOne.methodOne");
    }
}

class SubSystemTwo {
    public void methodTwo() {
        System.out.println("SubSystemTwo.methodTwo");
    }
}

class SubSystemThree {
    public void methodThree() {
        System.out.println("SubSystemThree.methodThree");
    }
}

class SubSystemFour {
    public void methodFour() {
        System.out.println("SubSystemFour.methodFour");
    }
}