package com.bbxyard.study.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

public class StringTest {

    @Test
    public void testJoiner() {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, "Poter", "J.K."));
        String s2 = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux").toString();
        System.out.println(s2);
    }

}