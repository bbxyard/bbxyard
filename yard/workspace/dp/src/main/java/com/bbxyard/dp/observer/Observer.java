package com.bbxyard.dp.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-15.
 */
public class Observer {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver(subject));
        subject.attach(new ConcreteObserver(subject));
        subject.attach(new ConcreteObserver(subject));
        subject.setSubjectState("当前状态为开!!");
        subject.notifyall();
        subject.setSubjectState("当前状态为关!!");
        subject.notifyall();
    }
}

interface IObserver {
    void update();
}

interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyall();
}

class Subject implements ISubject {
    public void attach(IObserver observer) {
        observers.add(observer);
    }
    public void detach(IObserver observer) {
        observers.remove(observer);
    }
    public void notifyall() {
        for (IObserver observer: observers) {
            observer.update();
        }
    }
    public Subject() {
        observers = new LinkedList<IObserver>();
    }
    private List<IObserver> observers;
}

class ConcreteSubject extends Subject {
    public ConcreteSubject() {
        subjectState = new String();
    }
    public String getSubjectState() {
        return subjectState;
    }
    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
    private String subjectState;
}


class ConcreteObserver implements IObserver {
    public void update() {
        observerState = subject.getSubjectState();
        System.out.printf("ConcreteObserver.update | observerState=%s //this=%s\n", observerState, toString());
    }
    public ConcreteObserver(ConcreteSubject subject) {
        observerState = new String();
        this.subject  = subject;
    }
    public String getObserverState() {
        return observerState;
    }
    public void setObserverState(String observerState) {
        this.observerState = observerState;
    }
    private String observerState;
    private ConcreteSubject subject;
}
