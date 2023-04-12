package com.jgc.springsecurity.test;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Calendar;

public class CalendarTest {

    private static final Logger log = LoggerFactory.getLogger(CalendarTest.class);

    @Test
    public void f1() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1970);
        c.set(Calendar.MONTH, 0);
        c.getTimeInMillis();
    }


    @Test
    public void f2() {
        Long oneDayTime = 24 * 60 *60 *1000L;
        Long oneHourTime = 60 * 60 * 1000L;
        Long time = 1205615475L;
        Long day = time / oneDayTime;
        Long hourTime = time %oneDayTime;
        Long hour = hourTime / oneHourTime;

        log.info(day.toString() + ", " + hour.toString());
    }

    @Test
    public void f3() {
        BigDecimal X = new BigDecimal(3843513.17877323);
        BigDecimal originX = new BigDecimal(3843513.18615704);
        BigDecimal originX2 = new BigDecimal(3843513.18615830);

        BigDecimal diffX = X.subtract(originX);
        BigDecimal diffX2 = X.subtract(originX2);
        BigDecimal diffOriginX = originX.subtract(originX2);
        log.info("diffX:{}, diffX2:{}, diffOriginX:{}", diffX, diffX2, diffOriginX);




    }
}
