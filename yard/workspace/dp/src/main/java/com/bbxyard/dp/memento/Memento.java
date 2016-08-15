package com.bbxyard.dp.memento;

import java.util.LinkedList;


/**
 * Created by bbxyard on 16-8-15.
 */
public class Memento {
    public static void main(String[] args) {
        Originator o = new Originator("Memento~How are you?");
        Caretaker  c = new Caretaker();
        // 输出初始状态
        o.show();
        c.add(o.backup()); // 备份

        // 修改状态
        o.update("Memoto~新状态~I'm great!");
        o.show();

        // 还原到上一个状态
        o.restore(c.get(-1));
        o.show();
    }
}

interface IMemento {
    void setState(State state);
    State getState();
}

class MementoImpl implements IMemento {
    public MementoImpl(State state) {
        this.state = (State)state.clone();
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }
    private State state;
}

class Originator {
    public Originator(String info) {
        this.state = new State(info);
    }
    // 备份快照
    public IMemento backup() {
        return new MementoImpl(state);
    }
    // 还原
    public void restore(IMemento m) {
        this.state = m.getState();
    }
    // 修改
    public void update(String info) {
        this.state.setField(info);
    }
    // show
    public void show() {
        System.out.println("Originator.show | state=" + state.toString());
    }
    private State state;
}

class Caretaker {
    public Caretaker() {
        history = new LinkedList<IMemento>();
    }
    public void add(IMemento m) {
        history.add(m);
    }
    public void remove(IMemento m) {
        history.remove(m);
    }
    public IMemento get(int index) {
        IMemento m = null;
        if (-1 == index && !history.isEmpty()) {
            m = history.getLast();
        } else {
            m = history.get(index);
        }
        return m;
    }
    
    private LinkedList<IMemento> history;
}

class State {
    public State(String info) {
        field = new String(info);
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    @Override
    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        return sbuf.append("field=[").append(field).append("]").toString();
    }
    @Override
    protected Object clone() {
        State state = new State(this.field);
        return state;
    }
    private String field;
}
