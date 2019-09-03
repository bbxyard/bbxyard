package com.yvhai.cookbook.dp.ext.singleton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingletonByHolderTest {

    @Test
    public void getInstance() {
        SingletonByHolder inst = SingletonByHolder.getInstance();
        System.out.println("SingletonByHolder uniq instance is: " + inst);
    }
}