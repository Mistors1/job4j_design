package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/MatrixResult")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String result = String.valueOf((i + 1) * (j + 1));
                    out.write(result.getBytes());
                    out.write(" ".getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
