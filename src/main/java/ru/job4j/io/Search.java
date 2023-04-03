package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.*;

public class Search {
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        search.validate(args);
        Path start = Paths.get(args[0]);
        String word = args[1];
        search.search(start, p -> p.toFile().getName().endsWith(word)).forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

    public void validate(String[] args) {
        if (args.length != 2
                || args[0] == null
                || args[1] == null) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
    }
}