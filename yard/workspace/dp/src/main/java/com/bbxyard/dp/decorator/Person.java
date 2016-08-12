package com.bbxyard.dp.decorator;

/**
 * Created by bbxyard on 16-8-11.
 */
public class Person {
    public Person() {
        this.name = "foobar";
    }
    public Person(String name) {
        this.name = name;
    }

    public void show()
    {
        System.out.printf("装妆的-%s   - \n", this.name);
    }

    public static void main(String[] args) {
        Person person = new Person("boxu");
        Finery tshirts = new TShirts();
        Finery pants = new BigPants();
        Finery sneakers = new Sneakers();
        Finery leatherShoes = new LeatherShoes();
        Finery tie = new Tie();
        Finery suit = new Suit();

        // 第一种装扮
        suit.decorate(tie);
        tie.decorate(leatherShoes);
        leatherShoes.decorate(pants);
        pants.decorate(tshirts);
        tshirts.decorate(sneakers);
        sneakers.decorate(person);
        System.out.print("第一种妆扮: ");
        suit.show();

        // 第二种妆扮
        suit.decorate(pants);
        pants.decorate(sneakers);
        sneakers.decorate(tie);
        tie.decorate(leatherShoes);
        leatherShoes.decorate(tshirts);
        tshirts.decorate(person);
        System.out.print("第二种妆扮: ");
        suit.show();
    }

    private String name;
}


/**
 * 服饰类
 */
class Finery extends Person {

    public void decorate(Person person) {
        this.person = person;
    }

    public void show() {
        if (this.person != null) {
            this.person.show();
        }
    }

    private Person person;
}


/**
 * 具体的服饰类
 */
class TShirts extends Finery {
    @Override
    public void show() {
        System.out.print("大体恤 ");
        super.show();
    }
}

class BigPants extends Finery {
    @Override
    public void show() {
        System.out.print("跨裤 ");
        super.show();
    }
}

class Sneakers extends Finery {
    @Override
    public void show() {
        System.out.print("破球鞋 ");
        super.show();
    }
}

class LeatherShoes extends Finery {
    @Override
    public void show() {
        System.out.print("皮鞋 ");
        super.show();
    }
}

class Tie extends Finery {
    @Override
    public void show() {
        System.out.print("领带 ");
        super.show();
    }
}

class Suit extends Finery {
    @Override
    public void show() {
        System.out.print("正装 ");
        super.show();
    }
}