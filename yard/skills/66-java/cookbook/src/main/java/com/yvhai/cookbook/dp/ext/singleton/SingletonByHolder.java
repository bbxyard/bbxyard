package com.yvhai.cookbook.dp.ext.singleton;


/**
 * 单例持有者模式
 */
public class SingletonByHolder {

    // 构造函数私有
    private SingletonByHolder() {
    }

    // 单例持有者
    private static class InstanceHolder {
        private final static SingletonByHolder uniqInstance = new SingletonByHolder();
    }

    public static SingletonByHolder getInstance() {
        return InstanceHolder.uniqInstance;
    }
}
