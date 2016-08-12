package com.bbxyard.dp.strategy;

/**
 * Created by bbxyard on 16-8-12.
 */
public class Strategy {
    public Strategy() {
    }

    public static void main(String[] args) {
        System.out.println("args = [" + args + "]");
        new Context(new ConcreteStrategyA()).execute();
        new Context(new ConcreteStrategyB()).execute();
        new Context(new ConcreteStrategyC()).execute();
    }
}

interface IStrategy {
    void alg();
}

class Context {
    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }
    // 上下文接口，处理具体对象
    public void execute() {
        strategy.alg();
    }
    private IStrategy strategy;
}


class ConcreteStrategyA implements IStrategy {
    public void alg() {
        System.out.println("ConcreteStrategyA.alg");
    }
}

class ConcreteStrategyB implements IStrategy {
    public void alg() {
        System.out.println("ConcreteStrategyB.alg");
    }
}

class ConcreteStrategyC implements IStrategy {
    public void alg() {
        System.out.println("ConcreteStrategyC.alg");
    }
}
