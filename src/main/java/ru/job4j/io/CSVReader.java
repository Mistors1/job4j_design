package ru.job4j.io;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String output = argsName.get("out");
        String filter = argsName.get("filter");
        String delimiter = argsName.get("delimiter");

        String[] filterPoint = filter.split(",");
        int[] filterIndex = new int[filterPoint.length];

        StringBuilder stringBuilder = new StringBuilder();
        List<String> result = new ArrayList<>();

        List<List<String>> phrase = read(path, delimiter);

        for (int i = 0; i < phrase.get(0).size(); i++) {
            for (int j = 0; j < filterPoint.length; j++) {
                if (phrase.get(0).get(i).equals(filterPoint[j])) {
                    filterIndex[j] = i;
                }
            }
        }
        for (List<String> strings : phrase) {
            for (int index : filterIndex) {
                stringBuilder.append(strings.get(index)).append(delimiter);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(System.lineSeparator());
        }
        result.add(String.valueOf(stringBuilder));
        save(output, result);
    }

    public static List<List<String>> read(String path, String delimiter) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            scanner.useDelimiter(System.lineSeparator());
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList.stream()
                .map(s -> List.of(s.split(delimiter)))
                .toList();
    }

    public static void save(String output, List<String> strings) {
        try (PrintStream out = "stdout".equals(output) ? System.out : new PrintStream(output)) {
            strings.forEach(out::print);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException();
        }
        ArgsName argsName = new ArgsName().of(args);
        argsName.validate(args);
        handle(argsName);
    }
}
