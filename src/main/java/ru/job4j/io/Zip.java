package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toString())))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args) {
        ArgsName argsName = new ArgsName().of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
        String directoryPath = argsName.get("d");
        String outputPath = argsName.get("o");
        File directory = new File(directoryPath);
        File outputFile = new File(outputPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Error: Directory does not exist");
        }
        if (!outputFile.exists() || outputFile.isDirectory()) {
            throw new IllegalArgumentException("Error: Output file does not exist or is a directory");
        }
        argsName.validate(args);
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = new ArgsName().of(args);
        Zip zip = new Zip();
        Search search = new Search();
        zip.validate(args);
        List<Path> searchList = search.search(Path.of("C:\\projects\\job4j_design\\"),
                p -> p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(searchList, Path.of(argsName.get("o")));
    }
}
