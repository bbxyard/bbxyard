package com.bbxyard.dp.Composite;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Composite {
    public static void main(String[] args) {
        TreeDemo.demo();
        System.out.println("Composite.main.done!!");
    }
}

class TreeDemo {
    public static void demo() {
        // # 建树
        Component root = new Branch("tree");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Component comp = new Branch("Brach X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));
        root.add(comp);

        Component comp2 = new Branch("Brach XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));
        comp.add(comp2);

        Leaf leaf = new Leaf("Leaf C");
        leaf.add(new Leaf("Leaf erroor")); // here, return not supp
        root.add(leaf);

        // # 遍历树
        root.display(1);
        System.out.println("TreeDemo.demo.done!");
    }
}

abstract class Component {
    abstract void add(Component c);
    abstract void remove(Component c);
    abstract void display(int depth);

    public Component(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    protected void showName(int depth) {
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < depth; ++i)
            sbuf.append('-');
        sbuf.append(getName());
        System.out.println(sbuf.toString());
    }
    private String name;
}

class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }
    @Override
    public void add(Component c) {
        System.out.println("Leaf.add | WAN SKIP! NOT SUPP!");
    }
    @Override
    public void remove(Component c) {
        System.out.println("Leaf.remove | WAN SKIP! NOT SUPP!");
    }
    @Override
    public void display(int depth) {
        showName(depth);
    }
}

class Branch extends Component {
    public Branch(String name) {
        super(name);
        children = new LinkedList<Component>();
    }

    @Override
    void add(Component c) {
        children.add(c);
    }
    @Override
    void remove(Component c) {
        children.remove(c);
    }
    @Override
    void display(int depth) {
        showName(depth);
        for (Component c: children) {
            c.display(depth + 2);
        }
    }

    private List<Component> children;
}
