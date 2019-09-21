package com.bbxyard.study.hutool;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Calendar;

public class DateUtilTest {

    @Test
    public void test1() {

        System.out.println(DateUtil.date());
        System.out.println(DateUtil.date(Calendar.getInstance()));
        System.out.println(DateUtil.date(System.currentTimeMillis()));
        System.out.println(DateUtil.now());
        System.out.println(DateUtil.today());
    }

}
