package com.bbxyard.dp.singleton;

/**
 * Created by bbxyard on 16-8-11.
 */
public class Singleton {

    /* 私有构造方法，防止被实例化 */
    private Singleton() {
        System.out.println("Singlenton uniq inst!!");
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        private static Singleton inst = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonFactory.inst;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }


    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
