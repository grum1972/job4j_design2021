package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Oleg Kotov";
        int age = 50;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte num = 8;
        LOG.debug("Byte is : {} bit", num);
        char symbol = 'A';
        LOG.debug("This is symbol : {}", symbol);
        float pi = 3.14F;
        LOG.debug("This is number PI : {}", pi);
        long population = 7_000_000_000L;
        LOG.debug("This is population on Earth : {}", population);
        double mean = 2.3456778;
        LOG.debug("This is just double number : {}", mean);
        boolean isTrue = true;
        LOG.debug("This is trust : {}", isTrue);
        short s = 32767;
        LOG.debug("This is short var : {}", s);
    }
}