package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines()
                    .filter(s -> !s.contains("#") && s.contains("="))
                    .forEach(string -> {
                        String[] buffer = string.split("=", 2);
                        if (buffer.length > 1) {
                            values.put(buffer[0], buffer[1]);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
      String result = values.get(key);
      if (!values.containsKey(key)) {
          throw new NoSuchElementException("Element not found");
      }
      if ("".equals(key) || "".equals(result)) {
          throw new IllegalArgumentException();
      }
      return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}