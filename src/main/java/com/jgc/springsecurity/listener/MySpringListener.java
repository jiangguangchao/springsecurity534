package com.jgc.springsecurity.listener;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: springsecurity534
 * @description: 自定义spring listener
 * @author:
 * @create: 2021-09-24 14:41
 */

//@Component
public class MySpringListener implements ApplicationListener {

    private static final Logger log = LoggerFactory.getLogger(MySpringListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("监听到的事件：{}" , event);
    }
}
