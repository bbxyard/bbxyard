package com.yvhai.cookbook.dp.ext.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonByEnumTest {

    @Test
    public void getInstance() {
        SingletonByEnum uniqInstance = SingletonByEnum.getInstance();
        System.out.println("SingletonByEnum Uniq Instance is: " + uniqInstance);
    }
}
