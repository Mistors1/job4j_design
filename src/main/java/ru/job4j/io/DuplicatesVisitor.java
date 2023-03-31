package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Set<FileProperty>  set = new HashSet<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!set.add(fileProperty)) {
            System.out.println(file.getFileName());
        }
        return super.visitFile(file, attrs);
    }
}