package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;


public class SearchFiles implements FileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> list = new ArrayList<>();
    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            list.add(file.toAbsolutePath());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public List<Path> getPath() {
        return list;
    }
}
