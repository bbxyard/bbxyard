package com.bbxyard.dp.mediator;

/**
 * Created by bbxyard on 16-8-15.
 */
public class Mediator {
    public static void main(String[] args) {
        ConcreteMediator    mediator = new ConcreteMediator();
        ConcreteColleague1  colleague1 = new ConcreteColleague1("boxu", mediator);
        ConcreteColleague2  colleague2 = new ConcreteColleague2(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.sendmsg("how do you do?");
        colleague2.sendmsg("Guten Tag! @2016.08.15");

        System.out.println("Mediator.main.done!!");
    }
}

abstract class Colleague {
    public Colleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public void sendmsg(String msg) {
        mediator.sendmsg(msg, this);
    }

    abstract void notify(String msg);
    private IMediator mediator;
}


interface IMediator {
    void sendmsg(String msg, Colleague colleague);
}

class ConcreteMediator implements IMediator {
    public void sendmsg(String msg, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.notify(msg);
        } else if (colleague == colleague2) {
            colleague1.notify(msg);
        } else {
            System.out.println("ConcreteMediator.sendmsg|discard!! " + msg);
        }
    }

    public void setColleague1(Colleague colleague1) {
        this.colleague1 = colleague1;
    }
    public void setColleague2(Colleague colleague2) {
        this.colleague2 = colleague2;
    }

    private Colleague   colleague1;
    private Colleague   colleague2;
}

class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(String name, IMediator mediator) {
        super(mediator);
        this.name = name;
    }

    @Override
    public void sendmsg(String msg) {
        System.out.printf("ConcreteColleague1.sendmsg|同事-1(%s)ready to send: %s\n", name, msg);
        super.sendmsg(msg);
    }

    @Override
    void notify(String msg) {
        System.out.printf("ConcreteColleague1.notify|同事-1(%s)收到消息:%s\n", name, msg);
    }
    private String name;
}

class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(IMediator mediator) {
        super(mediator);
    }
    @Override
    void notify(String msg) {
        System.out.printf("ConcreteColleague2.notify|同事-2(%s)收到消息:%s\n", "xx", msg);
    }
}