package ru.job4j.io;

import java.util.*;
import java.io.*;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> buffered = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
           buffered.add(in.lines().filter(s -> s.contains(" 404 "))
                   .toList().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffered;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log");
        System.out.println(log);

    }
}
