package com.yvhai.cookbook.dp.ext.singleton;

/**
 * 基于 双重检查 + volatile
 * 避免 指令重排 问题
 */

public class SingletonByDC {

    private static volatile SingletonByDC instance;

    private SingletonByDC() {}

    public static SingletonByDC getInstance() {
        if (instance == null) {
            synchronized (SingletonByDC.class) {
                if (instance == null) {
                    instance = new SingletonByDC();
                }
            }
        }
        return instance;
    }
}
