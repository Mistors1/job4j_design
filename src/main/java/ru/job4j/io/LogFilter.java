package ru.job4j.io;

import java.util.*;
import java.io.*;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> buffered = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
           buffered.addAll(in.lines().filter(s -> s.contains(" 404 "))
                   .toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffered;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            out.println(String.join(System.lineSeparator(), log));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log");
        save(log, "data/404");
        System.out.println(String.join(System.lineSeparator(), log));

    }
}
