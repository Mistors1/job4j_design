package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            List<String[]> list = in.lines()
                    .map(s -> s.split(" "))
                    .toList();
            boolean isWork = true;
            for (String[] s : list) {
                if (isWork && (s[0].equals("400") || s[0].equals("500"))) {
                    isWork = false;
                    out.printf("%s;", s[1]);
                }
                if (!isWork && (s[0].equals("200") || s[0].equals("300"))) {
                    isWork = true;
                    out.printf("%S;%n", s[1]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}