package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            String str;
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    str = (i * j) + " ";
                    out.write(str.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
