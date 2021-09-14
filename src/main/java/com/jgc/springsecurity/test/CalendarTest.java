package com.jgc.springsecurity.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class CalendarTest {

    private static final Logger log = LoggerFactory.getLogger(CalendarTest.class);

    @Test
    public void f1() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2020);
        c.set(Calendar.MONTH, 5);
        c.getTimeInMillis();
    }
}
