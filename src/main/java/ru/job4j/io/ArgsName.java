package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .forEach(s -> {
                    String[] buffer = s.split("=", 2);
                    String[] key = buffer[0].split("-", 2);
                    values.put(key[1], buffer[1]);
                });

    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.validate(args);
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    private void validate(String[] args) {
        Arrays.stream(args)
                .forEach(s -> {
                    if (!s.startsWith("-")) {
                        throw new IllegalArgumentException("Error: This argument '" + s
                                + "' does not start with a '-' character");
                    }
                    if (!s.contains("=")) {
                        throw new IllegalArgumentException("Error: This argument '" + s
                                + "' does not contain an equal sign");
                    }
                    String[] buffer = s.split("=", 2);
                    if (buffer[0] == null || buffer[0].equals("-")) {
                        throw new IllegalArgumentException("Error: This argument '" + s
                                + "' does not contain a key");
                    }
                    if (Objects.equals(buffer[1], "") || buffer[1] == null) {
                        throw new IllegalArgumentException("Error: This argument '" + s
                                + "' does not contain a value");
                    }
                });

    }
}