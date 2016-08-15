package com.bbxyard.dp.state;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-15.
 */
public class State {
    public static void main(String[] args) {
        Context ctx = new ContextEx();
        ctx.resetState(Condition.COND_A);
        ctx.request();
        ctx.resetState(Condition.COND_Z);
        ctx.request();
        ctx.resetState(Condition.COND_B);
        ctx.request();
        ctx.resetState(Condition.COND_C);
        ctx.request();
        ctx.resetState(Condition.COND_NULL);
        System.out.println("State.main.done!!");
    }
}


interface IState {
    void handle(Context ctx);
}


class Context {
//    public Context(IState state) { // 外部传入初始状态
//        this.state = state;
//        this.condition = Condition.COND_NULL;
//    }
    public Context() {
        this.state = new ConcreteStateA(); // 直接定义初始状态, 托管内存
        this.condition = Condition.COND_NULL;
    }
    // 处理外部请求
    public void request() {
        state.handle(this);
    }
    // 内部状态切换
    public void updateState(IState state) {
        this.state = state;
    }
    // 重置状态
    public void resetState(Condition cond) {
        this.condition  = cond;
        updateState(new ConcreteStateA());
    }
    // 条件状态验证模拟
    public boolean verifyState(Condition cond) {
        return condition.verify(cond);
    }
    protected IState state;
    private Condition condition;
}

/**
 * Java语言，会自动托管内存，但C++不行
 * 以下针对C++通用模型
 */
class ContextEx extends Context {
    public ContextEx() {
        super();
        historyStates = new LinkedList<IState>();
        historyStates.add(state);
    }

    @Override
    public void updateState(IState state) {
        historyStates.add(state);
        super.updateState(state);
    }
    @Override
    public void resetState(Condition cond) {
        clear();
        super.resetState(cond);
    }

    private void clear() {
        if (!historyStates.isEmpty()) {
            // 释放历史状态
            System.out.println("ContextEx.resetState | history visit: ");
            for (IState state : historyStates) {
                System.out.printf("  ==> %s\n", state.getClass().toString());
                // 若为C++此处释放内存.
                // ...
            }
            System.out.println("ContextEx.resetState | history done!");
            // 清空列表
            historyStates.clear();
        }
    }

    private List<IState>    historyStates;
}

class ConcreteStateA implements IState {
    public void handle(Context ctx) {
        if (ctx.verifyState(Condition.COND_A)) {
            System.out.println("ConcreteStateA.handle.done!!");
        } else {
            ctx.updateState(new ConcreteStateB());
            ctx.request();
        }
    }
}

class ConcreteStateB implements IState {
    public void handle(Context ctx) {
        if (ctx.verifyState(Condition.COND_B)) {
            System.out.println("ConcreteStateB.handle.done!!");
        } else {
            ctx.updateState(new ConcreteStateC());
            ctx.request();
        }
    }
}

class ConcreteStateC implements IState {
    public void handle(Context ctx) {
        if (ctx.verifyState(Condition.COND_C)) {
            System.out.println("ConcreteStateC.handle.done!!");
        } else {
            ctx.updateState(new ConcreteStateZ());
            ctx.request();
        }
    }
}

class ConcreteStateZ implements IState {
    public void handle(Context ctx) {
        if (ctx.verifyState(Condition.COND_Z)) {
            System.out.println("ConcreteStateZ.handle.done!!");
        } else {
            System.out.println("ConcreteStateZ.handle!! not supp!!");
        }
    }
}


/**
 * 条件抽象
 */
enum Condition {

    COND_NULL("null", 0),
    COND_A("A", 1), COND_B("B", 2), COND_C("C", 3),
    COND_Z("Z", 26);

    Condition(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 条件抽象
    public boolean verify(Condition cond) {
        return cond == this;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    private String name;
    private int index;
}
