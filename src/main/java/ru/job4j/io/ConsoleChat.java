package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private class ConsoleInput {
        private Scanner scanner = new Scanner(System.in);

        public String askUser(String question) {
            System.out.print(question);
            return scanner.nextLine();
        }
    }

    private String randomPhrase(List<String> list) {
        int i = (int) (Math.random() * list.size());
        return list.get(i);
    }

    public void run() {
        ConsoleInput input = new ConsoleInput();
        List<String> log = new ArrayList<>();
        List<String> list = this.readPhrases();
        boolean run = true;
        boolean isBotSpeak = true;
        String answerBot = "";
        while (run) {
            String answer = input.askUser("Пользователь: ");
            switch (answer) {
                case OUT:
                    System.out.println("Программа: ПОКА !");
                    run = false;
                    log.add(answer);
                    this.saveLog(log);
                    break;
                case STOP:
                    System.out.println("Программа: Пойду отдохну !");
                    log.add(answer);
                    isBotSpeak = false;
                    break;
                case CONTINUE:
                    System.out.println("Программа: Я вернулся!");
                    isBotSpeak = true;
                default:
                    log.add(answer);
                    if (isBotSpeak) {
                        answerBot = randomPhrase(list);
                        System.out.println("Программа: " + answerBot);
                        log.add(answerBot);
                    }
            }
        }
    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            String line;
            while ((line = br.readLine()) != null) {
                listPhrases.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;

    }

    private void saveLog(List<String> log) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(path,
                Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/text.log", "./data/bot_answer.txt");
        cc.run();
    }
}
