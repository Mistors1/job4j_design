package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void run() {
        List<String> answers = readPhrases();
        StringBuilder stringBuilder = new StringBuilder();
        String user;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                user = in.readLine();
                stringBuilder.append(System.lineSeparator()).append("user: ").append(user);
                if (user.equals(STOP)) {
                    user = in.readLine();
                    stringBuilder.append(System.lineSeparator()).append("user: ").append(user);
                    while (!user.equals(CONTINUE)) {
                        user = in.readLine();
                        stringBuilder.append(System.lineSeparator()).append("user: ").append(user);
                    }
                }
                int random = (int) (Math.random() * answers.size());
                String botPhrases = answers.get(random);
                System.out.println(botPhrases);
                stringBuilder.append(System.lineSeparator()).append("bot: ").append(botPhrases);
            } while (!user.equals(OUT));
            saveLog(Collections.singletonList(stringBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(botAnswers)))) {
            in.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/botLog", "data/botAnswers");
        cc.run();
    }
}
