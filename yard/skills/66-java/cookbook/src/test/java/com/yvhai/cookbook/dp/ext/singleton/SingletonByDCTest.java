package com.yvhai.cookbook.dp.ext.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonByDCTest {

    @Test
    public void getInstance() {

        SingletonByDC uniqInstance = SingletonByDC.getInstance();
        System.out.println("SingletonByDC Uniq Instance is: " + uniqInstance);

    }
}