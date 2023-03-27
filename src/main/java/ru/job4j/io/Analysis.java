package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analysis {
    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            in.lines()
                    .forEach(s -> {
                        boolean isWork = true;
                        String[] split = s.split(" ", 2);
                        if (!isWork) {
                            builder.append(split[1] + ";");
                        }
                        if (split[0].equals("500") || split[0].equals("400")) {
                            isWork = false;
                        } else  if (split[0].equals("200") || split[0].equals("300")) {
                            isWork = true;
                        }

                    });
            out.println(builder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}