package com.yvhai.cookbook.dp.ext.singleton;

public class SingletonByEnum {
    private SingletonByEnum() {

    }

    public static SingletonByEnum getInstance() {
        SingletonByEnum instance = Rep.INSTANCE.getInstance();
        return instance;
    }

    private enum Rep {
        INSTANCE;

        private final SingletonByEnum uniqInstance;

        Rep() {
            uniqInstance = new SingletonByEnum();
        }

        private SingletonByEnum getInstance() {
            return uniqInstance;
        }
    }
}
