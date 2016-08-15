package com.bbxyard.dp.command;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-15.
 */
public class Command {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        IReciver fixedReciverA = new ReciverA();
        invoker.add(new ConcreteCommandA(fixedReciverA));
        invoker.add(new ConcreteCommandA(fixedReciverA));
        invoker.add(new ConcreteCommandB());
        invoker.add(new ConcreteCommandB());
        // 提交请求
        invoker.commit();
        System.out.println("Command.main.done!!");
    }
}


/**
 * 命令抽象类
 */
interface ICommand {
    void execute();
}

/**
 * 具体干活的
 */
interface IReciver {
    void handle();
}

class Invoker {
    public Invoker() {
        commands = new LinkedList<ICommand>();
    }

    public void add(ICommand cmd) {
        commands.add(cmd);
        System.out.println("Invoker.add | cmd=" + cmd.toString());
    }
    public void remove(ICommand cmd) {
        commands.remove(cmd);
        System.out.println("Invoker.remove | cmd=" + cmd.toString());
    }

    // 提交订单
    public void commit() {
        for (ICommand cmd: commands) {
            cmd.execute();
        }
    }

    private List<ICommand> commands;
}

class ReciverA implements IReciver {
    public void handle() {
        System.out.println("ReciverA.handle.done +++ " + toString());
    }
}

class ReciverB implements IReciver {
    public void handle() {
        System.out.println("ReciverB.handle.done --- " + toString());
    }
}

class ConcreteCommandA implements ICommand {
    public ConcreteCommandA(IReciver reciver) {
        this.reciver = reciver;
    }
    public void execute() {
        reciver.handle();
    }
    private IReciver reciver;
}

class ConcreteCommandB implements ICommand {
    public ConcreteCommandB() {
        this.reciver = new ReciverB();
    }
    public void execute() {
        reciver.handle();
    }
    private IReciver reciver;
}