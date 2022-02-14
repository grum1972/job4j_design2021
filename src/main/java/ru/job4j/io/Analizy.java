package ru.job4j.io;

import java.io.*;

public class Analizy {
    @SuppressWarnings("checkstyle:SimplifyBooleanExpression")
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String strLog = "";
            boolean status = true;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] words = line.split(" ");
                if (("400".equals(words[0]) || "500".equals(words[0]))) {
                    if (status) {
                        strLog = words[1] + ";";
                        status = false;
                    }
                }
                if ("200".equals(words[0]) || "300".equals(words[0])) {
                    if (!status) {
                        strLog += words[1];
                        out.println(strLog);
                        status = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analyze = new Analizy();
        analyze.unavailable("server.log", "target.log");
    }
}
