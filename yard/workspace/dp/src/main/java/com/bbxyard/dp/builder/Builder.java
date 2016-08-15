package com.bbxyard.dp.builder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bbxyard on 16-8-14.
 */
public class Builder {
    public static void main(String[] args) {
        Director director = new Director();
        director.addBuilder(new ConcreteBuilderOne());
        director.addBuilder(new ConcreteBuilderTwo());
        director.construct();
        System.out.println("Builder.main.done!");
    }
}
class Product {
    public void add(String part) {
        parts.add(part);
    }
    public void show() {
        System.out.println("Product.show.begin");
        for (String part: parts) {
            System.out.printf("Part: %s\n", part);
        }
        System.out.println("Product.show.end");
    }
    public Product() {
        parts = new ArrayList<String>();
    }
    private List<String>    parts;
}

interface IBuilder {
    void buildPartA();
    void buildPartB();
    Product getResult();
}

class Director {
    public void construct() {
        for (IBuilder builder: builders) {
            builder.buildPartA();
            builder.buildPartB();
            builder.getResult().show();
        }
    }
    public void addBuilder(IBuilder builder) {
        builders.add(builder);
    }

    Director() {
        builders = new LinkedList<IBuilder>();
    }
    private List<IBuilder> builders;
}

class ConcreteBuilderOne implements IBuilder {
    public void buildPartA() {
        product.add("部件AAA");
    }
    public void buildPartB() {
        product.add("部件BBB");
    }
    public Product getResult() {
        return product;
    }
    public ConcreteBuilderOne() {
        product = new Product();
    }
    private Product product;
}

class ConcreteBuilderTwo implements IBuilder {
    public void buildPartA() {
        product.add("部件5A");
    }
    public void buildPartB() {
        product.add("部件5B");
    }
    public Product getResult() {
        return product;
    }
    public ConcreteBuilderTwo() {
        product = new Product();
    }
    private Product product;
}
