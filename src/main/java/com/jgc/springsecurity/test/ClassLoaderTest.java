package com.jgc.springsecurity.test;

import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.Role;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.Test;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-15 15:55
 */
public class ClassLoaderTest {

    private static final Logger log = LoggerFactory.getLogger(ClassLoaderTest.class);


    @Test
    public void f() {
        Object obj = new Object();
        String str = "a";
        Person p = new Person();
        Person p2 = new Person();
        Role role = new Role();
        System.out.println("classloader:" + obj.getClass().getClassLoader());
        System.out.println("classloader2:" + str.getClass().getClassLoader());
        System.out.println("classloader3:" + p.getClass().getClassLoader());
        System.out.println("classloader4:" + p2.getClass().getClassLoader());
        System.out.println("classloader5:" + role.getClass().getClassLoader());
    }
}
