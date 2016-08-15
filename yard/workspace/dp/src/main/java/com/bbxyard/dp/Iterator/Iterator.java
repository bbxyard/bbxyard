package com.bbxyard.dp.Iterator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Iterator {
    public static void main(String[] args) {
        ConcreteAggregate   aggregate = new ConcreteAggregate();
        aggregate.add("boxu");
        aggregate.add("wechar~xb0718");
        aggregate.add("programmer");
        aggregate.add("bbxyard@gmail.com");
        IIterator iter = aggregate.createIterator();
        System.out.println("Iterator.main | first=" + iter.first());
        while (!iter.isdone()) {
            System.out.println("Iterator.main | next=" + iter.currentItem());
            iter.next();
        }
        System.out.println("Iterator.main.done!!");
    }
}


interface IIterator {
    Object first();
    Object next();
    boolean isdone();
    Object currentItem();
}

interface IAggregate {
    IIterator createIterator();
}

class ConcreteIterator implements IIterator {
    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        this.cursor = 0;
    }

    public Object first() {
        return aggregate.getItem(0);
    }
    public Object next() {
        Object obj = currentItem();
        ++cursor;
        return obj;
    }
    public boolean isdone() {
        return (cursor == aggregate.getCount());
    }
    public Object currentItem() {
        Object obj = isdone()? null: aggregate.getItem(cursor);
        return obj;
    }

    private ConcreteAggregate aggregate;
    private int cursor;
}

class ConcreteAggregate implements IAggregate {
    public ConcreteAggregate() {
        items = new LinkedList<Object>();
    }

    public IIterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int getCount() {
        return  items.size();
    }
    public Object getItem(int index) {
        return items.get(index);
    }
    public void add(Object obj) {
        items.add(obj);
    }

    private List<Object> items;
}