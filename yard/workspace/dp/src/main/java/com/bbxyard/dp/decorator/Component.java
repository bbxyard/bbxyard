package com.bbxyard.dp.decorator;

/**
 * Created by bbxyard on 16-8-12.
 */
public class Component {
    public Component() {
    }

    public void operate() {
        System.out.println("Base created");
    }

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Decorator d1 = new ConcreteDecoratorA();
        Decorator d2 = new ConcreteDecoratorB();
        d1.setComponent(component);
        d2.setComponent(d1);
        d2.operate();
    }
}

class ConcreteComponent extends Component {
    @Override
    public void operate() {
        System.out.println("具体对象操作");
    }
}

class Decorator extends Component {
    @Override
    public void operate() {
        if (component != null) {
            component.operate();
        }
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    // 聚合原有的组件
    private Component component;
}


class ConcreteDecoratorA extends Decorator {
    @Override
    public void operate() {
        super.operate();
        addedState = "New State";
        System.out.printf("具体装饰对象A的操作 %s\n", addedState);
    }

    private String addedState;
}

class ConcreteDecoratorB extends Decorator {
    @Override
    public void operate() {
        super.operate();
        addedBehavior();
        System.out.printf("具体装饰对象B的操作\n");
    }

    private void addedBehavior() {
        System.out.printf("---对象B的扩展动作\n");
    }
}