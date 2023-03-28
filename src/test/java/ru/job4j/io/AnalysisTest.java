package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 10:56:01");
            out.println("500 10:57:01");
            out.println("300 10:58:01");
            out.println("300 10:59:01");
            out.println("300 11:01:02");
            out.println("500 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(builder::append);
        }
        assertThat(builder.toString()).hasToString("10:56:01;10:58:01;" +
                "11:02:02;");
    }

}