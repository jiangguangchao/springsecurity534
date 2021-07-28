package com.jgc.springsecurity.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
    
    private static final Logger log = LoggerFactory.getLogger(Person.class);
    
    private String name;
    
    public String readBook(String bookName) {
        log.info("I am reading " + bookName);
        return "I am reading " + bookName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
