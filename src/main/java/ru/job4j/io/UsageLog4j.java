package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        double d = 13.2;
        float f = 11f;
        long l = 1111;
        byte b = 1;
        boolean bo = true;
        char c = 'a';
        short s = 222;
        LOG.debug("User info name : {}, age : {}, {},{} ,{} ,{} ,{} ,{} ,{} ", name, age, d, f, l, b, bo, c, s);
    }
}
